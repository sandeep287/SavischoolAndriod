package com.savitroday.savischools.api;


import com.savitroday.savischools.api.response.UserOAuthResponse;

import java.util.HashMap;

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
    
    
    @FormUrlEncoded
    @POST("api/account/CheckEmailForForgotPass")
    CustomCallAdapter.CustomCall<HashMap<String, String>> validateEmail(@Field("emailId") String emailId,
                                                                        @Field("schoolId") String schoolId);
    
    
    @FormUrlEncoded
    @POST("api/account/CheckOtpForForgotPass")
    CustomCallAdapter.CustomCall<HashMap<String, String>> verifyOtpCode(@Field("emailId") String emailId,
                                                                        @Field("schoolId") String schoolId,
                                                                        @Field("otp") int otp);
    
    
    @FormUrlEncoded
    @POST("api/account/ChangeForgotPass")
    CustomCallAdapter.CustomCall<HashMap<String, String>> changePassword(@Field("emailId") String emailId,
                                                                         @Field("schoolId") String schoolId,
                                                                         @Field("otp") int otp,
                                                                         @Field("password") String password);
}
