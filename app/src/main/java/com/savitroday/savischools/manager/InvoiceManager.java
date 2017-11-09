package com.savitroday.savischools.manager;

import android.util.Log;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.api.ApiException;
import com.savitroday.savischools.api.CustomCallAdapter;
import com.savitroday.savischools.api.UserRestService;
import com.savitroday.savischools.api.response.Invoice;
import com.savitroday.savischools.api.response.Invoices;
import com.savitroday.savischools.util.Constants;
import com.savitroday.savischools.util.TinyDB;

import java.util.ArrayList;
import java.util.List;

import bolts.Task;
import bolts.TaskCompletionSource;
import retrofit2.Response;

/**
 * Created by Harshita Ahuja on 18/09/17.
 */

public class InvoiceManager {
    public static final String TAG = "InvoiceManager";
    public static Boolean updateInProgress = Boolean.FALSE;
    private UserRestService userRestService;
    private ArrayList<TaskCompletionSource> taskList = new ArrayList<>();
    private static Invoices invoices;
    private static boolean clearCache = false;
    private TinyDB tinyDB;
    
    public InvoiceManager(UserRestService service, TinyDB tinyDB) {
        this.userRestService = service;
        this.tinyDB = tinyDB;
        
    }
    
    public Task getInvoicesTask() {
        
        final TaskCompletionSource<Invoices> task = new TaskCompletionSource<Invoices>();
        if (invoices != null && !clearCache && !updateInProgress) {
            task.trySetResult(invoices);
            return task.getTask();
        }
        if (updateInProgress) {
            //add in task as already called
            taskList.add(task);
        } else {
            updateInProgress = true;
            String userId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_USER_ID);
            String schoolId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_SCHOOL_ID);
            userRestService.getInvoiceByStudent(schoolId, userId).enqueue(new CustomCallAdapter
                                                                                .CustomCallback<Invoices>() {
                @Override
                public void success(Response<Invoices> response) {
                    invoices = response.body();

                    task.setResult(invoices);
                    for (TaskCompletionSource taskCompletionSource : taskList) {
                        taskCompletionSource.setResult(invoices);
                    }
                    taskList.clear();
                    updateInProgress = false;
                    clearCache = false;
                }
                
                @Override
                public void failure(ApiException e) {
                    task.setError(e);
                    for (TaskCompletionSource taskCompletionSource : taskList) {
                        taskCompletionSource.setError(new Exception(e.getMessage()));
                    }
                    taskList.clear();
                    updateInProgress = false;
                }
            });
        }
        return task.getTask();
    }
    
    public List<Invoice> getPendingInvoices() {
        List<Invoice> pendingInvoices = new ArrayList<>();
        List<Invoice> invoices1=invoices.Listinvoices;
        for (int i=0;i<invoices1.size();i++) {
            Invoice invoice=invoices1.get(i);
            if (invoice.status.equals("Pending")) {
                pendingInvoices.add(invoice);
                Log.e("cclkncskc","cjkcjcjlacb");
            }
        }
        return pendingInvoices;
    }
    public List<Invoice> getHistoyrInvoices() {
        List<Invoice> historyInvoices = new ArrayList<>();
        List<Invoice> invoices1=invoices.Listinvoices;
        for (int i=0;i<invoices1.size();i++) {
            Invoice invoice=invoices1.get(i);
            if (invoice.status.equals("Paid")) {
                historyInvoices.add(invoice);

            }
        }
        return historyInvoices;
    }
    
}
