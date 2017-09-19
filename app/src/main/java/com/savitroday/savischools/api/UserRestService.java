package com.savitroday.savischools.api;



import retrofit2.http.GET;

/**
 * Created by harshitaahuja.
 */
public interface UserRestService {
    
    /* ================
        SAMPLE
     ================ */
    @GET("http://schlmappdev.savitech.us/oauth/token")
    CustomCallAdapter.CustomCall<String> login();
    
    
}
