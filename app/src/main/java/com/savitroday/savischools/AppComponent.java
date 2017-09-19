package com.savitroday.savischools;


import com.savitroday.savischools.activity.MainActivity;
import com.savitroday.savischools.api.RestHelper;
import com.savitroday.savischools.di.module.AppModule;
import com.savitroday.savischools.di.module.ManagerModule;
import com.savitroday.savischools.di.module.NonAuthModule;
import com.savitroday.savischools.di.module.UserRestModule;
import com.savitroday.savischools.manager.MyProfileManager;

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