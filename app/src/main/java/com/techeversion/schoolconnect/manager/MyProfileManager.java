package com.techeversion.schoolconnect.manager;

import com.techeversion.schoolconnect.api.UserRestService;
import com.techeversion.schoolconnect.util.TinyDB;

import javax.inject.Inject;

import bolts.Task;

/**
 * Created by Harshita Ahuja on 18/09/17.
 */

public class MyProfileManager {
    public static final String TAG = "MyProfileManager";
    public static Boolean updateInProgress = Boolean.FALSE;
    private UserRestService userRestService;
    private TinyDB tinyDB;
    public MyProfileManager(UserRestService service, TinyDB tinyDB) {
        this.userRestService = service;
        this.tinyDB = tinyDB;

    }
    
    public Task getMyProfileTask(){
        return null;
    }
}
