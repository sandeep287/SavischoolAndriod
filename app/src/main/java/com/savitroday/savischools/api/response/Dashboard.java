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
    @SerializedName("parentId")
    @Expose
    public String parentId;
    @SerializedName("userId")
    @Expose
    public String userId;
    @SerializedName("parentName")
    @Expose
    public String parentName;
    @SerializedName("totalMessagesNotification")
    @Expose
    public Integer totalMessagesNotification;
    @SerializedName("unreadMessagesNotification")
    @Expose
    public Integer unreadMessagesNotification;
    @SerializedName("ListStudentModel")
    @Expose
    public List<Student> listStudentModel = null;
    @SerializedName("ListSchoolMessagesModel")
    @Expose
    public List<Message> listSchoolMessagesModel = null;
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
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
        if (checkin != null)
            date += format.format(checkin);
        return date;
    }
    
    public String getCheckOutTime() {
        String date = "";
        Date checkout = getDefaultStudent().checkout;
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
        if (checkout != null)
            date += format.format(checkout);
        else
            date += "NOT YET";
        return date;
    }
    
}