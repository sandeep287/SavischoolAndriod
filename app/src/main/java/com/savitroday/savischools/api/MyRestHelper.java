package com.savitroday.savischools.api;

import android.content.Intent;

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
//        Intent intent = new Intent(app.getApplicationContext(), SignUpHomeActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//        app.getApplicationContext().startActivity(intent);
    }
}
