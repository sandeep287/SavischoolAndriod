package com.techeversion.schoolconnect;


import com.techeversion.schoolconnect.di.module.AppModule;
import com.techeversion.schoolconnect.di.module.ManagerModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component( //contains all other modules/dependencies
        modules = {
                          AppModule.class,
                          ManagerModule.class
        }
)

public interface AppComponent {
    
    void inject(MyApplication application);
    
    
}