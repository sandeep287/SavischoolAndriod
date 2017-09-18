package com.techeversion.schoolconnect.helper;


import android.util.Log;

import com.techeversion.schoolconnect.MyApplication;
import com.techeversion.schoolconnect.api.ApiException;
import com.techeversion.schoolconnect.api.CustomCallAdapter;
import com.techeversion.schoolconnect.api.OAuthRestService;
import com.techeversion.schoolconnect.api.response.UserOAuthResponse;
import com.techeversion.schoolconnect.manager.MyProfileManager;
import com.techeversion.schoolconnect.util.Constants;

import javax.inject.Inject;

import bolts.Task;
import bolts.TaskCompletionSource;
import retrofit2.Response;

/**
 * Created by harshitaahuja.
 */

public class LoginHelper {
    
    
    private OAuthRestService oAuthRestService;
    private MyProfileManager myProfileManager;
    private String email, password;
    
    
    public void setCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    
    @Inject
    public LoginHelper(OAuthRestService oAuthRestService, MyProfileManager myProfileManager) {
        this.oAuthRestService = oAuthRestService;
        this.myProfileManager = myProfileManager;
    }
    
    public Task loginAndGetUser() {
        return login().continueWithTask((task) -> {
            if (task.isFaulted()) {
                return Task.forError(task.getError());
            } else {
                //return myProfileManager.getMyProfileTask();
                return task;
            }
        });
    }
    
    public Task getUser() {
        return myProfileManager.getMyProfileTask();
    }
    
    
    public Task<Boolean> login() {
        final TaskCompletionSource<Boolean> task = new TaskCompletionSource<Boolean>();
        
        if (MyApplication.tinyDB.getBoolean(Constants.SHARED_PREFERENCES_IS_LOGGED_IN, false)) { // facebook
            task.setResult(true);
        } else {
            asyncLogin(task);
        }
        
        return task.getTask();
    }
    
    public void asyncLogin(final TaskCompletionSource<Boolean> task) {
        CustomCallAdapter.CustomCall<UserOAuthResponse> loginCall = oAuthRestService.userLogin(this.password, this.email,
                "320", "password");
        loginCall.enqueue(new CustomCallAdapter.CustomCallback<UserOAuthResponse>() {
            @Override
            public void success(Response<UserOAuthResponse> response) {
                if (response.isSuccessful()) {
                    UserOAuthResponse userOAuthResponse = response.body();
                    MyApplication.tinyDB.putString(Constants.SHARED_PREFERENCES_ACCESS_TOKEN, userOAuthResponse
                                                                                                      .accessToken);
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
