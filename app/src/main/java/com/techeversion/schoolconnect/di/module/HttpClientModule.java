package com.techeversion.schoolconnect.di.module;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by harshitaahuja.
 */

@Module(
        includes = AppModule.class
)
public class HttpClientModule {
    
    static int MAX_AGE = 86400;
    static int MAX_STALE = 86400;
    static int CACHE_SIZE = 20;
    
    @Provides
    public OkHttpClient provideOkHttpClient(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), "cache_file");
        Cache cache = new Cache(httpCacheDirectory, CACHE_SIZE * 1024 * 1024);
        
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        
        OkHttpClient client = new OkHttpClient.Builder()
                                      .connectTimeout(60, TimeUnit.SECONDS)
                                      .writeTimeout(30, TimeUnit.SECONDS)
                                      .readTimeout(60, TimeUnit.SECONDS)
                                      .cache(cache)
                /*.addInterceptor(chain -> {
                    Request originalRequest = chain.request();
                    CacheControl control = originalRequest.cacheControl();
                    boolean tr = control.maxAgeSeconds() == MAX_STALE;
                    String cacheHeaderValue = tr ? "public, max-age=" + MAX_AGE : "";
                    Request request = originalRequest.newBuilder().build();
                    Response response = chain.proceed(request);
                    return response.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .header("Cache-Control", cacheHeaderValue)
                            .build();
                })*/
                                      .addInterceptor(logInterceptor)
                                      .build();
        return client;
    }
}
