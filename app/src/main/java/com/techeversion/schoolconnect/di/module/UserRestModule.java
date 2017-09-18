package com.techeversion.schoolconnect.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.techeversion.schoolconnect.api.CustomCallAdapter;
import com.techeversion.schoolconnect.api.UserRestService;
import com.techeversion.schoolconnect.di.qualifiers.RestServiceOkHttpClient;
import com.techeversion.schoolconnect.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by harshitaahuja.
 */

@Module(
        includes = {AppModule.class, HttpClientModule.class}
)
public class UserRestModule {
    
    @Provides
    @Singleton
    @RestServiceOkHttpClient
    public OkHttpClient provideOkHttpClient(OkHttpClient okHttpClient, AppModule appModule) {
        return okHttpClient.newBuilder().build();//.addInterceptor(new UserAuthInterceptor(appModule)).build();
    }
    
    @Provides
    @Singleton
    public UserRestService provideRestService(@RestServiceOkHttpClient OkHttpClient okHttpClient, Context context) {
        //dependencies!
        
        
        Gson gson = new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd HH:mm:ss")
                            .create();
        
        Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(Constants.BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create(gson))
                                    .client(okHttpClient)
                                    .addCallAdapterFactory(new
                                                                   CustomCallAdapter.ErrorHandlingCallAdapterFactory(new
                                                                                                                             CustomCallAdapter.MainThreadExecutor(), context))
                                    .build();
        return retrofit.create(UserRestService.class);
    }
    
}


