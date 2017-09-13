/*
 * Copyright (c) 2011-present, salesforce.com, inc.
 * All rights reserved.
 * Redistribution and use of this software in source and binary forms, with or
 * without modification, are permitted provided that the following conditions
 * are met:
 * - Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * - Neither the name of salesforce.com, inc. nor the names of its contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission of salesforce.com, inc.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.techeversion.schoolconnect;


import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.google.firebase.iid.FirebaseInstanceId;

import com.techeversion.schoolconnect.di.module.AppModule;
import com.techeversion.schoolconnect.util.Constants;
import com.techeversion.schoolconnect.util.TinyDB;

import io.fabric.sdk.android.Fabric;


/**
 * Application class for our application.
 */
public class MyApplication extends Application {
    
    public static Context appContext;
    public static TinyDB tinyDB;
    private AppComponent appComponent;
    public static boolean isInBackground = true;
    
    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }
    
    public static MyApplication getApp() {
        return MyApplication.get(MyApplication.appContext);
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        appContext = getApplicationContext();
        appComponent = DaggerAppComponent.builder()
                               .appModule(new AppModule(this))
                               .build();
        appComponent.inject(this);
        
        //SharedPreferences Apis Initialization.
        SharedPreferences spPrivate = getSharedPreferences(Constants
                                                                   .SHARED_PREFERENCE_PRIVATE_NAME, MODE_PRIVATE);
        tinyDB = new TinyDB(spPrivate);
        
        
        String token = FirebaseInstanceId.getInstance().getToken();
       
        /*AnalyticsTrackers.initialize(this);
        gaTracker = AnalyticsTrackers.getInstance().get(AnalyticsTrackers.Target.APP);*/
        
        // Set up Crashlytics, disabled for debug builds
        Crashlytics crashlyticsKit = new Crashlytics.Builder()
                                             .core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
                                             .build();
        
        // Initialize Fabric with the debug-disabled crashlytics.
        Fabric.with(this, crashlyticsKit, new Crashlytics());
        
        // register to be informed of activities starting up
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            
            @Override
            public void onActivityCreated(Activity activity,
                                          Bundle savedInstanceState) {
                
                // new activity created; force its orientation to portrait
                activity.setRequestedOrientation(
                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
            
            @Override
            public void onActivityStarted(Activity activity) {
                /*ViewGroup group = (ViewGroup) activity.findViewById(android.R.id.content);
                TypefaceUtil.setAllTextView(group);*/
            }
            
            @Override
            public void onActivityResumed(Activity activity) {
                if (isInBackground) {
                    Log.d("", "app went to foreground");
                    isInBackground = false;
                }
            }
            
            @Override
            public void onActivityPaused(Activity activity) {
            }
            
            @Override
            public void onActivityStopped(Activity activity) {
            }
            
            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }
            
            @Override
            public void onActivityDestroyed(Activity activity) {
            }
            
            
        });
        
        registerComponentCallbacks(new ComponentCallbacks2() {
            @Override
            public void onTrimMemory(int i) {
                if (i == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN || i == ComponentCallbacks2.TRIM_MEMORY_COMPLETE) {
                    Log.d("", "app went to background");
                    isInBackground = true;
                }
            }
            
            @Override
            public void onConfigurationChanged(Configuration configuration) {
                
            }
            
            @Override
            public void onLowMemory() {
                
            }
        });
        
        
    }
    
    // To support API level 19
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        // MultiDex.install(this);
    }
    
    public AppComponent getComponent() {
        return appComponent;
    }
    
    
    public void clearData() {
        
    }
    
    
    public void logout(Activity activity) {
        MyApplication.tinyDB.putBoolean(Constants.SHARED_PREFERENCES_IS_LOGGED_IN, false);
        MyApplication.tinyDB.remove(Constants.SHARED_PREFERENCES_UUID);
        MyApplication.tinyDB.remove(Constants.SHARED_PREFERENCES_USER_TYPE);
        MyApplication.getApp().clearData();
    }
}

