package com.savitroday.savischools.manager;

import com.savitroday.savischools.api.UserRestService;
import com.savitroday.savischools.util.TinyDB;

import javax.inject.Inject;

import bolts.Task;

/**
 * Created by Harshita Ahuja on 18/09/17.
 */

public class DashboardManager {
    public static final String TAG = "StudentManager";
    public static Boolean updateInProgress = Boolean.FALSE;
    private UserRestService userRestService;
    private TinyDB tinyDB;
    public DashboardManager(UserRestService service, TinyDB tinyDB) {
        this.userRestService = service;
        this.tinyDB = tinyDB;

    }
    
    public Task getDashboardTask(){
        return null;
    }
}
