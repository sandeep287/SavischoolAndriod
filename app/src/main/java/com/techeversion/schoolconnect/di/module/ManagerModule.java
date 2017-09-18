package com.techeversion.schoolconnect.di.module;




import com.techeversion.schoolconnect.api.UserRestService;
import com.techeversion.schoolconnect.manager.MyProfileManager;
import com.techeversion.schoolconnect.util.TinyDB;

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
    public MyProfileManager provideMyProfileManager(UserRestService restService, TinyDB tinyDB) { //dependencies!
        return new MyProfileManager(restService, tinyDB);
    }
    
}

