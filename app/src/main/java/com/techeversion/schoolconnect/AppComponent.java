package com.techeversion.schoolconnect;


import com.techeversion.schoolconnect.activity.MainActivity;
import com.techeversion.schoolconnect.api.RestHelper;
import com.techeversion.schoolconnect.di.module.AppModule;
import com.techeversion.schoolconnect.di.module.ManagerModule;
import com.techeversion.schoolconnect.di.module.NonAuthModule;
import com.techeversion.schoolconnect.di.module.UserRestModule;
import com.techeversion.schoolconnect.manager.MyProfileManager;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component( //contains all other modules/dependencies
        modules = {
                          AppModule.class,
                          ManagerModule.class,
                          NonAuthModule.class,
                          UserRestModule.class
        }
)

public interface AppComponent {
    
    void inject(MyApplication application);
    
    void inject(RestHelper restHelper);
    
    void inject(MainActivity mainActivity);
    
    MyProfileManager profileManager();
    
}