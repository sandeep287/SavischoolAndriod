package com.savitroday.savischools.manager;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.api.ApiException;
import com.savitroday.savischools.api.CustomCallAdapter;
import com.savitroday.savischools.api.UserRestService;
import com.savitroday.savischools.api.response.Dashboard;
import com.savitroday.savischools.api.response.ParentProfile;
import com.savitroday.savischools.util.Constants;
import com.savitroday.savischools.util.TinyDB;

import java.util.ArrayList;

import bolts.Task;
import bolts.TaskCompletionSource;
import retrofit2.Response;

/**
 * Created by Harshita Ahuja on 03/10/17.
 */

public class MyProfileManager {
    public static final String TAG = "MyProfileManager";
    public static Boolean updateInProgress = Boolean.FALSE;
    private UserRestService userRestService;
    private ArrayList<TaskCompletionSource> taskList = new ArrayList<>();
    private static ParentProfile parentProfile;
    private static boolean clearCache = false;
    private TinyDB tinyDB;
    
    public MyProfileManager(UserRestService service, TinyDB tinyDB) {
        this.userRestService = service;
        this.tinyDB = tinyDB;
        
    }
    
    public Task getMyProfileTask() {
        
        final TaskCompletionSource<ParentProfile> task = new TaskCompletionSource<ParentProfile>();
        
        if (parentProfile != null && !clearCache && !updateInProgress) {
            task.trySetResult(parentProfile);
            return task.getTask();
        }
        if (updateInProgress) {
            //add in task as already called
            taskList.add(task);
        } else {
            updateInProgress = true;
            String parentId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_PARENT_ID);
            String schoolId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_SCHOOL_ID);
            
            userRestService.getParentProfile(schoolId, parentId).enqueue(new CustomCallAdapter
                                                                                         .CustomCallback<ParentProfile>() {
                @Override
                public void success(Response<ParentProfile> response) {
                    parentProfile = response.body();
                    task.setResult(parentProfile);
                    for (TaskCompletionSource taskCompletionSource : taskList) {
                        taskCompletionSource.setResult(parentProfile);
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
