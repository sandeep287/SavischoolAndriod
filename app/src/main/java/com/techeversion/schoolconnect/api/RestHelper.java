package com.techeversion.schoolconnect.api;


import com.techeversion.schoolconnect.di.module.AppModule;

import javax.inject.Inject;

/**
 * Created by harshitaahuja.
 */

public abstract class RestHelper {
    
    public static final String TAG = "RestHelper";
    AppModule appModule;
    @Inject
    UserRestService userRestService;
    
    @Inject
    OAuthRestService oAuthRestService;
    
    public RestHelper(AppModule appModule) {
        this.appModule = appModule;
        appModule.provideApplication().getComponent().inject(this);
    }
    
    
    public abstract void logoutUser();
}
