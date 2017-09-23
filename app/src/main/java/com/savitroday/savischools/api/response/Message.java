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
    @SerializedName("delFlg")
    @Expose
    public Boolean delFlg;
    @SerializedName("createdOn")
    @Expose
    public String createdOn;
    @SerializedName("createdBy")
    @Expose
    public String createdBy;
    @SerializedName("IsNotification")
    @Expose
    public Boolean isNotification;
    @SerializedName("updatedOn")
    @Expose
    public Object updatedOn;
    @SerializedName("updatedBy")
    @Expose
    public Object updatedBy;
    @SerializedName("schoolId")
    @Expose
    public Integer schoolId;
    @SerializedName("staffId")
    @Expose
    public String staffId;
    @SerializedName("classSectionId")
    @Expose
    public String classSectionId;
    @SerializedName("sectionStudentId")
    @Expose
    public Object sectionStudentId;
    @SerializedName("sessionId")
    @Expose
    public String sessionId;
    @SerializedName("classId")
    @Expose
    public String classId;
    @SerializedName("Curriculum")
    @Expose
    public Object curriculum;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("referenceNumberCHAR")
    @Expose
    public Object referenceNumberCHAR;
    @SerializedName("ImageData")
    @Expose
    public Object imageData;
    @SerializedName("mediaPath")
    @Expose
    public Object mediaPath;
    @SerializedName("attachment")
    @Expose
    public Object attachment;
    @SerializedName("expiresOn")
    @Expose
    public Object expiresOn;
    @SerializedName("closed")
    @Expose
    public Boolean closed;
    @SerializedName("phonesms")
    @Expose
    public Boolean phonesms;
    @SerializedName("phonesmsSection")
    @Expose
    public Boolean phonesmsSection;
    @SerializedName("closedOn")
    @Expose
    public Object closedOn;
    @SerializedName("StudentId")
    @Expose
    public String studentId;
    @SerializedName("StaffName")
    @Expose
    public Object staffName;
    @SerializedName("StudentName")
    @Expose
    public String studentName;
    @SerializedName("Class")
    @Expose
    public Object _class;
    @SerializedName("Status")
    @Expose
    public Object status;
    @SerializedName("ClassSectionName")
    @Expose
    public Object classSectionName;
    @SerializedName("PrimeryPhone")
    @Expose
    public Object primeryPhone;
    @SerializedName("Name")
    @Expose
    public Object name;
    @SerializedName("SMSPhoneList")
    @Expose
    public Object sMSPhoneList;
    @SerializedName("ClassdisplaySeq")
    @Expose
    public Integer classdisplaySeq;
    @SerializedName("SectiondisplaySeq")
    @Expose
    public Integer sectiondisplaySeq;
}
