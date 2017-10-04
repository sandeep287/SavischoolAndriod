package com.savitroday.savischools.manager;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.api.ApiException;
import com.savitroday.savischools.api.CustomCallAdapter;
import com.savitroday.savischools.api.UserRestService;
import com.savitroday.savischools.api.response.Assignment;
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

public class AssignmentManager {
    public static final String TAG = "AssignmentManager";
    public static Boolean updateInProgress = Boolean.FALSE;
    private UserRestService userRestService;
    private ArrayList<TaskCompletionSource> taskList = new ArrayList<>();
    private static List<Assignment> assignmentList;
    private static boolean clearCache = false;
    private TinyDB tinyDB;
    
    public AssignmentManager(UserRestService service, TinyDB tinyDB) {
        this.userRestService = service;
        this.tinyDB = tinyDB;
        
    }
    
    public Task getAssignmentTask() {
        
        final TaskCompletionSource<List<Assignment>> task = new TaskCompletionSource<List<Assignment>>();
        
        if (assignmentList != null && !clearCache && !updateInProgress) {
            task.trySetResult(assignmentList);
            return task.getTask();
        }
        if (updateInProgress) {
            //add in task as already called
            taskList.add(task);
        } else {
            updateInProgress = true;
            String parentId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_PARENT_ID);
            String schoolId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_SCHOOL_ID);
            
            userRestService.getStudentAssignment(schoolId, parentId).enqueue(new CustomCallAdapter
                                                                                .CustomCallback<List<Assignment>>() {
                @Override
                public void success(Response<List<Assignment>> response) {
                    assignmentList = response.body();
                    task.setResult(assignmentList);
                    for (TaskCompletionSource taskCompletionSource : taskList) {
                        taskCompletionSource.setResult(assignmentList);
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
  
    
}
