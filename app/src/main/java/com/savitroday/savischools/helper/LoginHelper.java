package com.savitroday.savischools.helper;


import android.util.Log;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.api.ApiException;
import com.savitroday.savischools.api.CustomCallAdapter;
import com.savitroday.savischools.api.OAuthRestService;
import com.savitroday.savischools.api.response.UserOAuthResponse;
import com.savitroday.savischools.manager.StudentManager;
import com.savitroday.savischools.util.Constants;

import javax.inject.Inject;

import bolts.Task;
import bolts.TaskCompletionSource;
import retrofit2.Response;

/**
 * Created by harshitaahuja.
 */

public class LoginHelper {
    
    
    private OAuthRestService oAuthRestService;
    private StudentManager studentManager;
    private String email, password, schoolId;
    
    
    public void setCredentials(String email, String password,String schoolId) {
        this.email = email;
        this.password = password;
        this.schoolId = schoolId;
    }
    
    
    @Inject
    public LoginHelper(OAuthRestService oAuthRestService, StudentManager studentManager) {
        this.oAuthRestService = oAuthRestService;
        this.studentManager = studentManager;
    }
    
    public Task loginAndGetUser() {
        return login().continueWithTask((task) -> {
            if (task.isFaulted()) {
                return Task.forError(task.getError());
            } else {
                //return myProfileManager.getMyProfileTask();
                return Task.forResult(task.getResult());
            }
        });
    }
    
    public Task getUser() {
        return studentManager.getMyProfileTask();
    }
    
    
    public Task<Boolean> login() {
        final TaskCompletionSource<Boolean> task = new TaskCompletionSource<Boolean>();
        
//        if (MyApplication.tinyDB.getBoolean(Constants.SHARED_PREFERENCES_IS_LOGGED_IN, false)) { // facebook
//            task.setResult(true);
//        } else {
            asyncLogin(task);
       // }
        
        return task.getTask();
    }
    
    public void asyncLogin(final TaskCompletionSource<Boolean> task) {
        CustomCallAdapter.CustomCall<UserOAuthResponse> loginCall = oAuthRestService.userLogin(this.password, this.email,
                this.schoolId, "password");
        loginCall.enqueue(new CustomCallAdapter.CustomCallback<UserOAuthResponse>() {
            @Override
            public void success(Response<UserOAuthResponse> response) {
                if (response.isSuccessful()) {
                    UserOAuthResponse userOAuthResponse = response.body();
                    MyApplication.tinyDB.putString(Constants.SHARED_PREFERENCES_ACCESS_TOKEN, userOAuthResponse.accessToken);
                    MyApplication.tinyDB.putString(Constants.SHARED_PREFERENCES_SCHOOL_ID, userOAuthResponse.schoolid);
                    MyApplication.tinyDB.putString(Constants.SHARED_PREFERENCES_PARENT_ID, userOAuthResponse.parentid);
                    //if (!createUser) {
                    MyApplication.tinyDB.putBoolean(Constants.SHARED_PREFERENCES_IS_LOGGED_IN, true);
                    
                    task.setResult(true);
                    
                } else {
                    //TODO: raise exception
                    Log.e("Login", "login not successful");
                    task.setResult(false);
                }
            }
            
            @Override
            public void failure(ApiException e) {
                task.setError(e);
            }
        });
    }
    
}
