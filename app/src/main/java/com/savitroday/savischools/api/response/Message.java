package com.savitroday.savischools.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Harshita Ahuja on 18/10/17.
 */

public class Message {
    @Expose
    public String schoolMessageId;
    @SerializedName("createdOn")
    @Expose
    public String createdOn;
    @SerializedName("responseBy")
    @Expose
    public String responseBy;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("parentName")
    @Expose
    public String parentName;
    @SerializedName("staffName")
    @Expose
    public String staffName;
}
