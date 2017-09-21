package com.savitroday.savischools.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Harshita Ahuja on 22/09/17.
 */

public class Student {
    @SerializedName("schoolId")
    @Expose
    public Object schoolId;
    @SerializedName("StudentName")
    @Expose
    public String studentName;
    @SerializedName("Classname")
    @Expose
    public String classname;
    @SerializedName("Section")
    @Expose
    public Object section;
    @SerializedName("Curriculum")
    @Expose
    public Object curriculum;
    @SerializedName("teacherComments")
    @Expose
    public Object teacherComments;
    @SerializedName("remarks")
    @Expose
    public Object remarks;
    @SerializedName("submissionDate")
    @Expose
    public String submissionDate;
    @SerializedName("mediaPath")
    @Expose
    public Object mediaPath;
    @SerializedName("referenceNumberCHAR")
    @Expose
    public Object referenceNumberCHAR;
    @SerializedName("caption")
    @Expose
    public Object caption;
    @SerializedName("mediaId")
    @Expose
    public Object mediaId;
    @SerializedName("title")
    @Expose
    public Object title;
    @SerializedName("content")
    @Expose
    public Object content;
    @SerializedName("classSectionId")
    @Expose
    public String classSectionId;
    @SerializedName("dueDate")
    @Expose
    public Object dueDate;
    @SerializedName("rollNo")
    @Expose
    public Object rollNo;
    @SerializedName("studentAssignmentId")
    @Expose
    public String studentAssignmentId;
    @SerializedName("classAssignmentId")
    @Expose
    public String classAssignmentId;
    @SerializedName("sectionStudentId")
    @Expose
    public String sectionStudentId;
    @SerializedName("StudentmediaPath")
    @Expose
    public Object studentmediaPath;
    @SerializedName("Studentcaption")
    @Expose
    public Object studentcaption;
    @SerializedName("StudentCommets")
    @Expose
    public Object studentCommets;
    @SerializedName("Status")
    @Expose
    public Object status;
    @SerializedName("Phone")
    @Expose
    public String phone;
    @SerializedName("Email")
    @Expose
    public Object email;
    @SerializedName("StudentLastname")
    @Expose
    public Object studentLastname;
    @SerializedName("studentReceiptId")
    @Expose
    public String studentReceiptId;
    @SerializedName("country")
    @Expose
    public Object country;
    @SerializedName("state")
    @Expose
    public Object state;
    @SerializedName("city")
    @Expose
    public Object city;
    @SerializedName("amount")
    @Expose
    public Integer amount;
    @SerializedName("addressLine1")
    @Expose
    public Object addressLine1;
    @SerializedName("studentId")
    @Expose
    public String studentId;
    @SerializedName("addressLine2")
    @Expose
    public Object addressLine2;
    @SerializedName("postCode")
    @Expose
    public Object postCode;
    @SerializedName("transactionId")
    @Expose
    public Object transactionId;
    @SerializedName("invoiceNumber")
    @Expose
    public Object invoiceNumber;
}
