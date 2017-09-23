package com.savitroday.savischools.manager;

import com.savitroday.savischools.api.UserRestService;
import com.savitroday.savischools.util.TinyDB;

import bolts.Task;

/**
 * Created by Harshita Ahuja on 18/09/17.
 */

public class StudentManager {
    public static final String TAG = "StudentManager";
    public static Boolean updateInProgress = Boolean.FALSE;
    private UserRestService userRestService;
    private TinyDB tinyDB;
    public StudentManager(UserRestService service, TinyDB tinyDB) {
        this.userRestService = service;
        this.tinyDB = tinyDB;

    }
    
    public Task getMyProfileTask(){
        return null;
    }
}
