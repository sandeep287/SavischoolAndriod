package com.savitroday.savischools.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.api.MyRestHelper;
import com.savitroday.savischools.api.RestHelper;
import com.savitroday.savischools.util.Constants;
import com.savitroday.savischools.util.TinyDB;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    
    private MyApplication application;
    
    public AppModule(MyApplication application) {
        this.application = application;
    }
    
    @Provides
    @Singleton
    public MyApplication provideApplication() {
        return application;
    }
    
    @Provides
    @Singleton
    public Context provideContext() {
        return application.getApplicationContext();
    }
    
    @Provides
    @Singleton
    public TinyDB provideTinyDB() {
        SharedPreferences spPrivate = this.application.getSharedPreferences(Constants.SHARED_PREFERENCE_PRIVATE_NAME,
                Context.MODE_PRIVATE);
        return new TinyDB(spPrivate);
    }
    
    @Provides
    @Singleton
    public AppModule provideAppModule() {
        return this;
    }
    
    @Provides
    public RestHelper provideMyRestHelper(AppModule appModule) {
        return new MyRestHelper(appModule);
    }
}
