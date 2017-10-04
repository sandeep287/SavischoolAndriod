package com.savitroday.savischools.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
    
    @SerializedName("schoolId")
    @Expose
    public Integer schoolId;
    @SerializedName("studentName")
    @Expose
    public String studentName;
    @SerializedName("classname")
    @Expose
    public String classname;
    @SerializedName("section")
    @Expose
    public String section;
    @SerializedName("session")
    @Expose
    public String session;
    @SerializedName("isdefault")
    @Expose
    public Boolean isdefault;
    @SerializedName("curriculum")
    @Expose
    public String curriculum;
    @SerializedName("mediaPath")
    @Expose
    public String mediaPath;
    @SerializedName("iconMediaPath")
    @Expose
    public String iconMediaPath;
    @SerializedName("motherid")
    @Expose
    public String motherid;

    @SerializedName("motherPhone")
    @Expose
    public String motherPhone;

    @SerializedName("fatherid")
    @Expose
    public String fatherid;
    @SerializedName("fatherName")
    @Expose
    public String fatherName;
    @SerializedName("fatherPhone")
    @Expose
    public String fatherPhone;
    @SerializedName("motherName")
    @Expose
    public String motherName;
    @SerializedName("dateOfBirth")
    @Expose
    public Date dateOfBirth;
    @SerializedName("rollNo")
    @Expose
    public Object rollNo;
    @SerializedName("studentmediaPath")
    @Expose
    public String studentmediaPath;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("studentLastname")
    @Expose
    public String studentLastname;
    @SerializedName("addressLine1")
    @Expose
    public String addressLine1;
    @SerializedName("studentId")
    @Expose
    public String studentId;
    @SerializedName("addressLine2")
    @Expose
    public String addressLine2;
    @SerializedName("postCode")
    @Expose
    public String postCode;
    @SerializedName("checkin")
    @Expose
    public Date checkin;
    @SerializedName("checkout")
    @Expose
    public Date checkout;
    @SerializedName("gender")
    @Expose
    public Object gender;
    @SerializedName("bloodGroup")
    @Expose
    public Object bloodGroup;
    
    public Student(String studentName) {
        this.studentName = studentName;
    }
    
    public String getDateOfBirth(){
        return new SimpleDateFormat("dd MMM yyyy").format(dateOfBirth);
    }
    
}
