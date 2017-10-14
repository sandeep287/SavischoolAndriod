package com.savitroday.savischools.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.savitroday.savischools.util.DateUtility;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    @SerializedName("totalTime")
    @Expose
    public String totalTime;
    @SerializedName("createdOn")
    @Expose
    public Date createdOn;
    @SerializedName("isNotification")
    @Expose
    public Boolean isNotification;
    @SerializedName("hideOnMobile")
    @Expose
    public Boolean hideOnMobile;
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
    @SerializedName("studentName")
    @Expose
    public String studentName;
    @SerializedName("status")
    @Expose
    public Object status;
    @SerializedName("name")
    @Expose
    public Object name;
    @SerializedName("delFlg")
    @Expose
    public boolean delFlg;
    
    public String getCreatedOn(){
        return DateUtility.getDateFull(createdOn);
    }
    
}
