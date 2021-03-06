package com.savitroday.savischools.manager;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.api.ApiException;
import com.savitroday.savischools.api.CustomCallAdapter;
import com.savitroday.savischools.api.UserRestService;
import com.savitroday.savischools.api.response.Message;
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

public class NotificationManager {
    public static final String TAG = "NotificationManager";
    public static Boolean updateInProgress = Boolean.FALSE;
    private UserRestService userRestService;
    private ArrayList<TaskCompletionSource> taskList = new ArrayList<>();
    private static List<Message> messageList;
    private static boolean clearCache = false;
    private TinyDB tinyDB;
    
    public NotificationManager(UserRestService service, TinyDB tinyDB) {
        this.userRestService = service;
        this.tinyDB = tinyDB;
        
    }
    
    public Task getMessageTask() {
        
        final TaskCompletionSource<List<Message>> task = new TaskCompletionSource<List<Message>>();
        
        if (messageList != null && !clearCache && !updateInProgress) {
            task.trySetResult(messageList);
            return task.getTask();
        }
        if (updateInProgress) {
            //add in task as already called
            taskList.add(task);
        } else {
            updateInProgress = true;
            String parentId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_PARENT_ID);
            String schoolId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_SCHOOL_ID);
            
            userRestService.getMessages(schoolId, parentId).enqueue(new CustomCallAdapter
                                                                                .CustomCallback<List<Message>>() {
                @Override
                public void success(Response<List<Message>> response) {
                    messageList = response.body();
                    task.setResult(messageList);
                    for (TaskCompletionSource taskCompletionSource : taskList) {
                        taskCompletionSource.setResult(messageList);
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
    
    public Task readStatusUpdate(String schoolMessageId) {
        
        final TaskCompletionSource<Message> task = new TaskCompletionSource<Message>();
        
        
        String userId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_USER_ID);
        String schoolId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_SCHOOL_ID);
        
        userRestService.readStatusUpdate(schoolId, userId, schoolMessageId).enqueue(new CustomCallAdapter
                                                                                                .CustomCallback<Message>() {
            @Override
            public void success(Response<Message> response) {
                Message message = response.body();
                task.setResult(message);
            }
            
            @Override
            public void failure(ApiException e) {
                task.setError(e);
            }
        });
        
        return task.getTask();
    }
    
    
    public Task deleteMessageNotification(String schoolMessageId) {
        
        final TaskCompletionSource<Message> task = new TaskCompletionSource<Message>();
        
        
        String userId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_USER_ID);
        String schoolId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_SCHOOL_ID);
        
        userRestService.deleteMessageNotification(schoolId, userId, schoolMessageId).enqueue(new CustomCallAdapter
                                                                                                .CustomCallback<Message>() {
            @Override
            public void success(Response<Message> response) {
                Message message = response.body();
                task.setResult(message);
            }
            
            @Override
            public void failure(ApiException e) {
                task.setError(e);
            }
        });
        
        return task.getTask();
    }
    
}
