package com.savitroday.savischools.manager;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.api.ApiException;
import com.savitroday.savischools.api.CustomCallAdapter;
import com.savitroday.savischools.api.UserRestService;
import com.savitroday.savischools.api.response.Invoice;
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
    private static List<Invoice> invoiceList;
    private static boolean clearCache = false;
    private TinyDB tinyDB;
    
    public InvoiceManager(UserRestService service, TinyDB tinyDB) {
        this.userRestService = service;
        this.tinyDB = tinyDB;
        
    }
    
    public Task getInvoicesTask() {
        
        final TaskCompletionSource<List<Invoice>> task = new TaskCompletionSource<List<Invoice>>();
        
        if (invoiceList != null && !clearCache && !updateInProgress) {
            task.trySetResult(invoiceList);
            return task.getTask();
        }
        if (updateInProgress) {
            //add in task as already called
            taskList.add(task);
        } else {
            updateInProgress = true;
            String studentId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_STUDENT_ID);
            String schoolId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_SCHOOL_ID);
            userRestService.getInvoiceByStudent(schoolId, studentId).enqueue(new CustomCallAdapter
                                                                                .CustomCallback<List<Invoice>>() {
                @Override
                public void success(Response<List<Invoice>> response) {
                    invoiceList = response.body();
                    task.setResult(invoiceList);
                    for (TaskCompletionSource taskCompletionSource : taskList) {
                        taskCompletionSource.setResult(invoiceList);
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
        for (Invoice invoice:invoiceList) {
            if (invoice.status.equals("Pending")) {
                pendingInvoices.add(invoice);
                
            }
        }
        return pendingInvoices;
    }
    public List<Invoice> getHistoyrInvoices() {
        List<Invoice> historyInvoices = new ArrayList<>();
        for (int i=0;i<invoiceList.size();i++) {
            Invoice invoice=invoiceList.get(i);
            if (invoice.status.equals("Paid")) {
                historyInvoices.add(invoice);

            }
        }
        return historyInvoices;
    }
    public float getTotalAmount() {
        float totalAmount = 0;
        for (Invoice invoice:invoiceList) {
            if (invoice.status.equals("Pending")) {
                totalAmount += invoice.amount;
            }
        }
        return totalAmount;
    }
    
}
