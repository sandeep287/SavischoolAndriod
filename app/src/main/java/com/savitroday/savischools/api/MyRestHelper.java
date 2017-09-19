package com.savitroday.savischools.api;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.di.module.AppModule;

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
