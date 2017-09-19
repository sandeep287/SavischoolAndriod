package com.savitroday.savischools.manager;

import com.savitroday.savischools.api.UserRestService;
import com.savitroday.savischools.util.TinyDB;

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
