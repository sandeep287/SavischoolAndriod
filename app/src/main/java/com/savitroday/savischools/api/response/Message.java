package com.savitroday.savischools.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Harshita Ahuja on 23/09/17.
 */

public class Message {
    @SerializedName("schoolMessageId")
    @Expose
    public String schoolMessageId;
    @SerializedName("userId")
    @Expose
    public String userId;
    @SerializedName("createdOn")
    @Expose
    public String createdOn;
    @SerializedName("isNotification")
    @Expose
    public Boolean isNotification;
    @SerializedName("isViewed")
    @Expose
    public Boolean isViewed;
    @SerializedName("schoolId")
    @Expose
    public Integer schoolId;
    @SerializedName("staffId")
    @Expose
    public String staffId;
    @SerializedName("senderName")
    @Expose
    public String senderName;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("mediaPath")
    @Expose
    public String mediaPath;
    @SerializedName("iconMediaPath")
    @Expose
    public String iconMediaPath;
    @SerializedName("messageAttachment")
    @Expose
    public String messageAttachment;
    @SerializedName("expiresOn")
    @Expose
    public Object expiresOn;
    @SerializedName("closed")
    @Expose
    public Boolean closed;
    @SerializedName("closedOn")
    @Expose
    public Object closedOn;
    @SerializedName("studentId")
    @Expose
    public String studentId;
    @SerializedName("staffName")
    @Expose
    public Object staffName;
    @SerializedName("studentName")
    @Expose
    public String studentName;
    @SerializedName("className")
    @Expose
    public Object className;
    @SerializedName("status")
    @Expose
    public Object status;
    @SerializedName("classSectionName")
    @Expose
    public Object classSectionName;
    @SerializedName("name")
    @Expose
    public Object name;
}
