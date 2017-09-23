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
    @SerializedName("SchoolType")
    @Expose
    public String schoolType;
    @SerializedName("SchoolName")
    @Expose
    public String schoolName;
    @SerializedName("Schoolid")
    @Expose
    public String schoolid;
    @SerializedName("Phone")
    @Expose
    public String phone;
    @SerializedName("ContactPerson")
    @Expose
    public String contactPerson;
    @SerializedName("LogonId")
    @Expose
    public String logonId;
    @SerializedName("UserType")
    @Expose
    public String userType;
    @SerializedName("Status")
    @Expose
    public String status;
    @SerializedName("Parentid")
    @Expose
    public String parentid;
    @SerializedName("UserName")
    @Expose
    public String userName;
    @SerializedName("Gender")
    @Expose
    public String gender;
    @SerializedName(".issued")
    @Expose
    public String issued;
    @SerializedName(".expires")
    @Expose
    public String expires;
    
}


