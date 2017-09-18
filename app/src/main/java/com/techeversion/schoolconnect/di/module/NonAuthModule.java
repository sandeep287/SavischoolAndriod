package com.techeversion.schoolconnect.di.module;

import android.content.Context;

import com.techeversion.schoolconnect.api.CustomCallAdapter;
import com.techeversion.schoolconnect.api.OAuthRestService;
import com.techeversion.schoolconnect.di.qualifiers.ClientRestServiceAuthOkHttpClient;
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
public class NonAuthModule {
    
    @Provides
    @Singleton
    @ClientRestServiceAuthOkHttpClient
    public OkHttpClient provideOkHttpClient(OkHttpClient okHttpClient, AppModule appModule) {
        return okHttpClient.newBuilder().build();//addInterceptor(new NonAuthInterceptor()).build();
    }
    
    @Provides
    @Singleton
    public OAuthRestService provideRestService(@ClientRestServiceAuthOkHttpClient OkHttpClient okHttpClient, Context context) { //dependencies!
        
        
        Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(Constants.BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .client(okHttpClient)
                                    .addCallAdapterFactory(new
                                                                   CustomCallAdapter.ErrorHandlingCallAdapterFactory(new
                                                                                                                             CustomCallAdapter.MainThreadExecutor(), context))
                                    .build();
        return retrofit.create(OAuthRestService.class);
        
    }
}
