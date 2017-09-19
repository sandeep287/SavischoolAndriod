package com.savitroday.savischools.api;


import com.savitroday.savischools.api.response.UserOAuthResponse;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by harshitaahuja.
 */
public interface OAuthRestService {
    
    
    @FormUrlEncoded
    @POST("oauth/token")
    CustomCallAdapter.CustomCall<UserOAuthResponse> userLogin(@Field("password") String password,
                                                              @Field("userName") String username,
                                                              @Field("schoolid") String schoolId,
                                                              @Field("grant_type") String grant_type);
    
    
}
