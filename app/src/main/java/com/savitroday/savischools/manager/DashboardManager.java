package com.savitroday.savischools.manager;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.adapter.InvoiceAdapter;
import com.savitroday.savischools.api.ApiException;
import com.savitroday.savischools.api.CustomCallAdapter;
import com.savitroday.savischools.api.UserRestService;
import com.savitroday.savischools.api.response.Dashboard;
import com.savitroday.savischools.api.response.Invoice;
import com.savitroday.savischools.util.Constants;
import com.savitroday.savischools.util.TinyDB;
import com.savitroday.savischools.view.activity.MainActivity;

import java.util.List;

import bolts.Task;
import bolts.TaskCompletionSource;
import retrofit2.Response;

/**
 * Created by Harshita Ahuja on 18/09/17.
 */

public class DashboardManager {
    public static final String TAG = "StudentManager";
    public static Boolean updateInProgress = Boolean.FALSE;
    private UserRestService userRestService;
    private static Dashboard dashboard;
    private TinyDB tinyDB;
    
    public DashboardManager(UserRestService service, TinyDB tinyDB) {
        this.userRestService = service;
        this.tinyDB = tinyDB;
        
    }
    
    public Task getDashboardTask() {
        
        final TaskCompletionSource<Dashboard> task = new TaskCompletionSource<Dashboard>();
        
        
        String parentId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_PARENT_ID);
        String schoolId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_SCHOOL_ID);
        String userID = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_USER_ID);
        
        userRestService.getDashboard(schoolId, parentId, userID).enqueue(new CustomCallAdapter
                                                                                     .CustomCallback<Dashboard>() {
            @Override
            public void success(Response<Dashboard> response) {
                dashboard = response.body();
                task.setResult(dashboard);
            }
            
            @Override
            public void failure(ApiException e) {
                task.setError(e);
            }
        });
        return task.getTask();
    }
    
    void getInvoice(String schoolId, String studentId) {
        userRestService.getInvoiceByStudent(schoolId, studentId).enqueue(new CustomCallAdapter
                                                                                     .CustomCallback<List<Invoice>>() {
            
            @Override
            public void success(Response<List<Invoice>> response) {
                if (response.isSuccessful()) {
                    List<Invoice> invoiceList = response.body();
                  //  InvoiceAdapter adapter = new InvoiceAdapter(MainActivity.this, invoiceList);
                    // listView.setAdapter(adapter);
                }
            }
            
            @Override
            public void failure(ApiException e) {
                
            }
        });
    }
    
    void setDefaultStudent(int index){
        dashboard.getDefaultStudent().isdefault = false;
        dashboard.listStudentModel.get(index).isdefault = true;
    }
}
