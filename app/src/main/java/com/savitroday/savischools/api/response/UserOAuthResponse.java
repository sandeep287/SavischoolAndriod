package com.savitroday.savischools.api.response;

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
    @SerializedName("schoolType")
    @Expose
    public String schoolType;
    @SerializedName("schoolName")
    @Expose
    public String schoolName;
    @SerializedName("schoolId")
    @Expose
    public String schoolId;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("contactPerson")
    @Expose
    public String contactPerson;
    @SerializedName("logonId")
    @Expose
    public String logonId;
    @SerializedName("userId")
    @Expose
    public String userId;
    @SerializedName("userType")
    @Expose
    public String userType;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("parentid")
    @Expose
    public String parentid;
    @SerializedName("userName")
    @Expose
    public String userName;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName(".issued")
    @Expose
    public String issued;
    @SerializedName(".expires")
    @Expose
    public String expires;
    
}


