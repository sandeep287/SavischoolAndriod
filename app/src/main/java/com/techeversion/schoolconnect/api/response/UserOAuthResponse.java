package com.techeversion.schoolconnect.api.response;

/**
 * Created by Harshita Ahuja on 18/09/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserOAuthResponse {
    
    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("token_type")
    @Expose
    public String tokenType;
    @SerializedName("expires_in")
    @Expose
    public Integer expiresIn;
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("Schoolid")
    @Expose
    public String schoolId;
    @SerializedName("LogonId")
    @Expose
    public String loginId;
    @SerializedName("UserType")
    @Expose
    public String userType;
    @SerializedName("Satatus")
    @Expose
    public String status;
    @SerializedName("parentid")
    @Expose
    public String parentId;
    @SerializedName("studentList")
    @Expose
    public String studentList;
    @SerializedName(".issued")
    @Expose
    public String issued;
    @SerializedName(".expires")
    @Expose
    public String expires;
    
}