package com.techeversion.schoolconnect.api;

import com.techeversion.schoolconnect.MyApplication;
import com.techeversion.schoolconnect.di.module.AppModule;

/**
 * Created by harshitaahuja.
 */

public class MyRestHelper extends RestHelper {
    
    public MyRestHelper(AppModule appModule) {
        super(appModule);
    }
    
    @Override
    public void logoutUser() {
        MyApplication app = MyApplication.getApp();
        
        app.clearData();
        
        //send to login again
    }
}
