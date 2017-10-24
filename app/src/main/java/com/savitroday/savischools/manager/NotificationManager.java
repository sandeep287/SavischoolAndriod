package com.savitroday.savischools.manager;

import android.util.Log;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.api.ApiException;
import com.savitroday.savischools.api.CustomCallAdapter;
import com.savitroday.savischools.api.UserRestService;
import com.savitroday.savischools.api.response.Conversation;
import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.api.response.MessageNotification;
import com.savitroday.savischools.util.Constants;
import com.savitroday.savischools.util.TinyDB;

import java.util.ArrayList;
import java.util.HashMap;
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
    private static List<MessageNotification> messageNotificationList;
    private static boolean clearCache = false;
    private TinyDB tinyDB;
    
    public NotificationManager(UserRestService service, TinyDB tinyDB) {
        this.userRestService = service;
        this.tinyDB = tinyDB;
        
    }
    
    public Task getMessageTask() {
        
        final TaskCompletionSource<List<MessageNotification>> task = new TaskCompletionSource<List<MessageNotification>>();
        
        if (messageNotificationList != null && !clearCache && !updateInProgress) {
            task.trySetResult(messageNotificationList);
            return task.getTask();
        }
        if (updateInProgress) {
            //add in task as already called
            taskList.add(task);
        } else {
            updateInProgress = true;
            String userId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_USER_ID);
            String schoolId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_SCHOOL_ID);
            
            userRestService.getMessages(schoolId, userId).enqueue(new CustomCallAdapter
                                                                              .CustomCallback<List<MessageNotification>>() {
                @Override
                public void success(Response<List<MessageNotification>> response) {
                    List<MessageNotification> list = response.body();
                    sanitizeList(list);
                    task.setResult(messageNotificationList);
                    for (TaskCompletionSource taskCompletionSource : taskList) {
                        taskCompletionSource.setResult(messageNotificationList);
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
    
    
    void sanitizeList(List<MessageNotification> list) {
        messageNotificationList = new ArrayList<>();
        for (MessageNotification msg : list) {
            if (msg.hideOnMobile == false && msg.delFlg == false) {
                messageNotificationList.add(msg);
            }
        }
    }
    
    public Task readStatusUpdate(String schoolMessageId) {
        Log.e("mlkmklm","jknkjjk ");
        final TaskCompletionSource<MessageNotification> task = new TaskCompletionSource<MessageNotification>();
        
        
        String userId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_USER_ID);
        String schoolId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_SCHOOL_ID);
        
        userRestService.readStatusUpdate(schoolId, userId, schoolMessageId).enqueue(new CustomCallAdapter
                                                                                                .CustomCallback<MessageNotification>() {
            @Override
            public void success(Response<MessageNotification> response) {
                MessageNotification messageNotification = response.body();
                task.setResult(messageNotification);
            }
            
            @Override
            public void failure(ApiException e) {
                task.setError(e);
            }
        });
        
        return task.getTask();
    }
    
    
    public Task deleteMessageNotification(String schoolMessageId) {
        
        final TaskCompletionSource<MessageNotification> task = new TaskCompletionSource<MessageNotification>();
        
        
        String userId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_USER_ID);
        String schoolId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_SCHOOL_ID);
        
        HashMap<String, String> map = new HashMap<>();
        map.put("schoolMessageId", schoolMessageId);
        map.put("userId", userId);
        map.put("schoolId", schoolId);
        
        
        userRestService.deleteMessageNotification(map).enqueue(new CustomCallAdapter
                                                                           .CustomCallback<MessageNotification>() {
            @Override
            public void success(Response<MessageNotification> response) {
                MessageNotification messageNotification = response.body();
                task.setResult(messageNotification);
                clearCache = true;
                getMessageTask();
            }
            
            @Override
            public void failure(ApiException e) {
                task.setError(e);
            }
        });
        
        return task.getTask();
    }
    
    public Task getMessageConversation(String schoolMessageId) {
        
        final TaskCompletionSource<Conversation> task = new TaskCompletionSource<Conversation>();
        
        
        String schoolId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_SCHOOL_ID);
        
        userRestService.getMessageConversation(schoolId, schoolMessageId, false).enqueue(new CustomCallAdapter
                                                                                                     .CustomCallback<Conversation>() {
            @Override
            public void success(Response<Conversation> response) {
                Conversation conversation = response.body();
                task.setResult(conversation);
            }
            
            @Override
            public void failure(ApiException e) {
                task.setError(e);
            }
        });
        
        return task.getTask();
    }
    
    public Task replyToConversation(Message message ) {
        
        final TaskCompletionSource<List<Message>> task = new TaskCompletionSource<List<Message>>();

        userRestService.replyToConversation(message).enqueue(new CustomCallAdapter
                                                                         .CustomCallback<List<Message>>() {

            @Override
            public void success(Response<List<Message>> response) {
                List<Message> conversation = response.body();
                task.setResult(conversation);
            }
            
            @Override
            public void failure(ApiException e) {
                task.setError(e);
            }
        });
        
        return task.getTask();
    }
    
    public static List<MessageNotification>  getNOtificationList() {
        List<MessageNotification> notifications = new ArrayList<>();
        for (int i = 0; i < messageNotificationList.size(); i++) {


            if (messageNotificationList.get(i).isNotification&&messageNotificationList.get(i).delFlg!=true&&messageNotificationList.get(i).hideOnMobile!=true) {
                notifications.add(messageNotificationList.get(i));
            }
        }
        return notifications;
    }
    
    public static List<MessageNotification> getMessageNotificationList() {
        List<MessageNotification> messageNotifications = new ArrayList<>();
        for (int i = 0; i < messageNotificationList.size(); i++) {

            if ((!messageNotificationList.get(i).isNotification)&&(!messageNotificationList.get(i).delFlg)&&messageNotificationList.get(i).hideOnMobile!=true) {
                messageNotifications.add(messageNotificationList.get(i));
            }
        }
        return messageNotifications;
    }
    
    public static List<MessageNotification> getComunicationwith(String sendername) {
        List<MessageNotification> messageNotifications = new ArrayList<>();
        List<MessageNotification> onlymessageslist = new ArrayList<>();
        onlymessageslist = getMessageNotificationList();
        for (int i = onlymessageslist.size() - 1; i >= 0; i--) {
            
            if (sendername.equals(onlymessageslist.get(i).senderName)) {
                
                messageNotifications.add(onlymessageslist.get(i));
                
            }
        }
        
        return messageNotifications;
    }
    
}
