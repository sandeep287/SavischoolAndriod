package com.savitroday.savischools.api.intercepter;

import android.util.Log;

import com.savitroday.savischools.api.RestHelper;
import com.savitroday.savischools.di.module.AppModule;
import com.savitroday.savischools.util.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Rahul Gautam (RG) on 17/11/15.
 */
public class UserAuthInterceptor implements Interceptor {
    
    AppModule appModule;
    
    public UserAuthInterceptor(AppModule appModule) {
        this.appModule = appModule;
    }
    
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        String TAG = "UserAuthInterceptor";
        Log.e(TAG, "access token in db" + appModule.provideApplication().tinyDB.getString(Constants
                                                                                                  .SHARED_PREFERENCES_ACCESS_TOKEN));
        // Customize the request
        Request request = original.newBuilder()
                                  .header("Authorization", "Bearer " + appModule.provideApplication().tinyDB
                                                                               .getString(Constants
                                                                                                  .SHARED_PREFERENCES_ACCESS_TOKEN))
                                  .method(original.method(), original.body())
                                  .build();
        
        Response response = chain.proceed(request);
        if (response.code() == 401) {
            
            Log.e(TAG, "response false came for gettoken");
            RestHelper restHelper = appModule.provideMyRestHelper(appModule);
            restHelper.logoutUser();
            
        }
        // Customize or return the response
        return response;
    }
}
