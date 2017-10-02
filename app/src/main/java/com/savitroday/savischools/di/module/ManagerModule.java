package com.savitroday.savischools.di.module;




import com.savitroday.savischools.api.UserRestService;
import com.savitroday.savischools.manager.DashboardManager;
import com.savitroday.savischools.manager.MyProfileManager;
import com.savitroday.savischools.util.TinyDB;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by harshitaahuja
 */
@Module(
        includes = {AppModule.class}
)
public class ManagerModule {
    
    
    @Provides
    @Singleton
    public DashboardManager provideDashboardManager(UserRestService restService, TinyDB tinyDB) { //dependencies!
        return new DashboardManager(restService, tinyDB);
    }
    
    @Provides
    @Singleton
    public MyProfileManager provideMyProfileManager(UserRestService restService, TinyDB tinyDB) { //dependencies!
        return new MyProfileManager(restService, tinyDB);
    }
    
}

