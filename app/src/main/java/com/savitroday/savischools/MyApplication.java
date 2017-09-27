
package com.savitroday.savischools;


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

import com.savitroday.savischools.di.module.AppModule;
import com.savitroday.savischools.util.Constants;
import com.savitroday.savischools.util.TinyDB;

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

