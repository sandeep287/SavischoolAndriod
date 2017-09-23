package com.savitroday.savischools.di.module;




import com.savitroday.savischools.api.UserRestService;
import com.savitroday.savischools.manager.StudentManager;
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
    public StudentManager provideMyProfileManager(UserRestService restService, TinyDB tinyDB) { //dependencies!
        return new StudentManager(restService, tinyDB);
    }
    
}

