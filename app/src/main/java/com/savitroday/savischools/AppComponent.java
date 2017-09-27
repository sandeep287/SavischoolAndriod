package com.savitroday.savischools;


import com.savitroday.savischools.view.activity.LoginActivity;
import com.savitroday.savischools.view.activity.MainActivity;
import com.savitroday.savischools.api.RestHelper;
import com.savitroday.savischools.di.module.AppModule;
import com.savitroday.savischools.di.module.ManagerModule;
import com.savitroday.savischools.di.module.NonAuthModule;
import com.savitroday.savischools.di.module.UserRestModule;
import com.savitroday.savischools.view.fragment.DashboardFragment;
import com.savitroday.savischools.manager.DashboardManager;

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
    void inject(LoginActivity loginActivity);
    void inject(DashboardFragment dashboardFragment);
    
    DashboardManager profileManager();
    
}