package com.savitroday.savischools.api.response;

/**
 * Created by Harshita Ahuja on 22/09/17.
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Invoice {
    
    @SerializedName("studentInvoiceId")
    @Expose
    public String studentInvoiceId;
    @SerializedName("createdOn")
    @Expose
    public String createdOn;
    @SerializedName("invoiceType")
    @Expose
    public String invoiceType;
    @SerializedName("invoiceNumber")
    @Expose
    public String invoiceNumber;
    @SerializedName("invoiceDate")
    @Expose
    public Date invoiceDate;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("paymentTerm")
    @Expose
    public Object paymentTerm;
    @SerializedName("dueOn")
    @Expose
    public Date dueOn;
    @SerializedName("schoolId")
    @Expose
    public Integer schoolId;
    @SerializedName("sessionId")
    @Expose
    public String sessionId;
    @SerializedName("studentId")
    @Expose
    public String studentId;
    @SerializedName("amount")
    @Expose
    public Float amount;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("remarks")
    @Expose
    public String remarks;
    @SerializedName("studentName")
    @Expose
    public String studentName;
    @SerializedName("classId")
    @Expose
    public String classId;
    @SerializedName("curriculum")
    @Expose
    public String curriculum;
    @SerializedName("className")
    @Expose
    public String className;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("schoolName")
    @Expose
    public String schoolName;
    @SerializedName("year")
    @Expose
    public String year;
    @SerializedName("month")
    @Expose
    public String month;
    @SerializedName("rollNo")
    @Expose
    public Object rollNo;
    @SerializedName("paidAmount")
    @Expose
    public Float paidAmount;
    @SerializedName("mediaPath")
    @Expose
    public String mediaPath;
    @SerializedName("iconMediaPath")
    @Expose
    public String iconMediaPath;
    
    
    public String getDate(){
        return new SimpleDateFormat("dd").format(dueOn);
    }
    
    public String getMonth(){
        return new SimpleDateFormat("MMM").format(dueOn);
    }
    
    public String getYear(){
        return new SimpleDateFormat("yyyy").format(dueOn);
    }
    
}
