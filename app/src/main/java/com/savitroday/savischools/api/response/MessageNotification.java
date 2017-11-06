package com.savitroday.savischools.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.savitroday.savischools.util.DateUtility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Harshita Ahuja on 23/09/17.
 */

public class MessageNotification {
    @SerializedName("schoolMessageId")
    @Expose
    public String schoolMessageId;
    @SerializedName("messageAttachmentType")
    @Expose
    public String messageAttachmentType;
    @SerializedName("userId")
    @Expose
    public String userId;
    @SerializedName("totalTime")
    @Expose
    public String totalTime;
    @SerializedName("createdOn")
    @Expose
    public Date createdOn;
    @SerializedName("isNotification")
    @Expose
    public boolean isNotification;
    @SerializedName("hideOnMobile")
    @Expose
    public boolean hideOnMobile;
    @SerializedName("isViewed")
    @Expose
    public boolean isViewed;
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
    @SerializedName("iconMediaPath")
    @Expose
    public String iconMediaPath;
    @SerializedName("messageAttachment")
    @Expose
    public String messageAttachment;
    @SerializedName("expiresOn")
    @Expose
    public String expiresOn;
    @SerializedName("closed")
    @Expose
    public boolean closed;
    @SerializedName("closedOn")
    @Expose
    public String closedOn;
    @SerializedName("studentId")
    @Expose
    public String studentId;
    @SerializedName("studentName")
    @Expose
    public String studentName;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("delFlg")
    @Expose
    public boolean delFlg;
    
    public String getCreatedOn(){
        return DateUtility.getDateFull(createdOn);
    }
    
}
