package com.savitroday.savischools.api.response;

/**
 * Created by Harshita Ahuja on 17/10/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Conversation {
    
    @SerializedName("createdOn")
    @Expose
    public String createdOn;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("mediaPath")
    @Expose
    public String mediaPath;
    @SerializedName("messageAttachment")
    @Expose
    public String messageAttachment;
    @SerializedName("ListMessageResponseModel")
    @Expose
    public List<Message> messageList = null;
    
}
