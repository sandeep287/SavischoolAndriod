package com.savitroday.savischools.api.response;

/**
 * Created by Harshita Ahuja on 23/09/17.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Dashboard {
    
    @SerializedName("schoolId")
    @Expose
    public Integer schoolId;
    @SerializedName("userId")
    @Expose
    public String userId;
    @SerializedName("parentId")
    @Expose
    public String parentId;
    @SerializedName("parentName")
    @Expose
    public String parentName;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("emailId")
    @Expose
    public String emailId;
    @SerializedName("countryName")
    @Expose
    public String countryName;
    @SerializedName("stateName")
    @Expose
    public String stateName;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("postCode")
    @Expose
    public String postCode;
    @SerializedName("totalMessagesNotification")
    @Expose
    public int totalMessagesNotification;
    @SerializedName("unreadMessagesNotification")
    @Expose
    public int unreadMessagesNotification;
    @SerializedName("onlinePaymentIsAllow")
    @Expose
    public boolean onlinePaymentIsAllow;
    @SerializedName("nameOfGateway")
    @Expose
    public String nameOfGateway;
    @SerializedName("gatewayKey")
    @Expose
    public String gatewayKey;
    @SerializedName("gatewaySalt")
    @Expose
    public String gatewaySalt;
    @SerializedName("ListStudentModel")
    @Expose
    public List<Student> listStudentModel = null;
    @SerializedName("ListSchoolMessagesModel")
    @Expose
    public List<MessageNotification> listSchoolMessagesModel = null;
    @SerializedName("ListStudentInvoiceModel")
    @Expose
    public List<Invoice> listStudentInvoiceModel = null;
    
    
    public String getStudentName() {
        String name = "Hi " + getDefaultStudent().studentName + ",";
        return name;
    }
    
    public Student getDefaultStudent() {
        for (Student student : listStudentModel) {
            if (student.isdefault) {
                return student;
            }
        }
        return listStudentModel.get(0);
    }
    
    public String getCurrentDate() {
        String date = "Today, ";
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
        date += format.format(new Date());
        return date;
    }
    
    public String getCheckInTime() {
        String date = "";
        Date checkin = getDefaultStudent().checkin;
        SimpleDateFormat format = new SimpleDateFormat("H:mm a");
        if (checkin != null)
            date += format.format(checkin);
        return date;
    }
    
    public String getCheckOutTime() {
        String date = "";
        Date checkout = getDefaultStudent().checkout;
        SimpleDateFormat format = new SimpleDateFormat("H:mm a");
        if (checkout != null)
            date += format.format(checkout);
        else
            date += "NOT YET";
        return date;
    }
    
    public ParentProfile getParentProfile(){
        ParentProfile profile = new ParentProfile();
        profile.parentId = parentId;
        profile.firstName = parentName;
        profile.gender = gender;
        profile.phone1 = phone;
        profile.emailAddress = emailId;
        profile.countryName = countryName;
        profile.stateName = stateName;
        profile.city = city;
        profile.addressLine1 = address;
        profile.postCode = postCode;
        return profile;
    }
    
}