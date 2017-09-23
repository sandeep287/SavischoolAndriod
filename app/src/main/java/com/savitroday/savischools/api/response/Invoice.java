package com.savitroday.savischools.api.response;

/**
 * Created by Harshita Ahuja on 22/09/17.
 */

import java.util.Date;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Invoice {
    
    @SerializedName("studentInvoiceId")
    @Expose
    public String studentInvoiceId;
    @SerializedName("delFlg")
    @Expose
    public Boolean delFlg;
    @SerializedName("createdOn")
    @Expose
    public String createdOn;
    @SerializedName("createdBy")
    @Expose
    public String createdBy;
    @SerializedName("updatedOn")
    @Expose
    public Object updatedOn;
    @SerializedName("updatedBy")
    @Expose
    public Object updatedBy;
    @SerializedName("invoiceType")
    @Expose
    public String invoiceType;
    @SerializedName("invoiceNumber")
    @Expose
    public String invoiceNumber;
    @SerializedName("DesignId")
    @Expose
    public Object designId;
    @SerializedName("extraHoursAmount")
    @Expose
    public Integer extraHoursAmount;
    @SerializedName("IsextraHours")
    @Expose
    public Boolean isextraHours;
    @SerializedName("invoiceDate")
    @Expose
    public Date invoiceDate;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("paymentTerm")
    @Expose
    public String paymentTerm;
    @SerializedName("dueOn")
    @Expose
    public String dueOn;
    @SerializedName("monthlydate")
    @Expose
    public Object monthlydate;
    @SerializedName("schoolId")
    @Expose
    public Integer schoolId;
    @SerializedName("sessionId")
    @Expose
    public String sessionId;
    @SerializedName("studentId")
    @Expose
    public String studentId;
    @SerializedName("StudentIdsearch")
    @Expose
    public Object studentIdsearch;
    @SerializedName("amount")
    @Expose
    public Integer amount;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("serviceProvider")
    @Expose
    public Object serviceProvider;
    @SerializedName("remarks")
    @Expose
    public String remarks;
    @SerializedName("StudentName")
    @Expose
    public String studentName;
    @SerializedName("classId")
    @Expose
    public String classId;
    @SerializedName("productInfo")
    @Expose
    public Object productInfo;
    @SerializedName("Curriculum")
    @Expose
    public Object curriculum;
    @SerializedName("admittedOnSessionId")
    @Expose
    public Object admittedOnSessionId;
    @SerializedName("classSectionId")
    @Expose
    public String classSectionId;
    @SerializedName("Classname")
    @Expose
    public Object classname;
    @SerializedName("Section")
    @Expose
    public Object section;
    @SerializedName("Session")
    @Expose
    public Object session;
    @SerializedName("mobile")
    @Expose
    public Object mobile;
    @SerializedName("email")
    @Expose
    public Object email;
    @SerializedName("SchoolName")
    @Expose
    public Object schoolName;
    @SerializedName("year")
    @Expose
    public Object year;
    @SerializedName("month")
    @Expose
    public Object month;
    @SerializedName("SchoolAddress")
    @Expose
    public Object schoolAddress;
    @SerializedName("rollNo")
    @Expose
    public Object rollNo;
    @SerializedName("classmonthlyId")
    @Expose
    public Object classmonthlyId;
    @SerializedName("clientId")
    @Expose
    public Object clientId;
    @SerializedName("paidamount")
    @Expose
    public Integer paidamount;
    @SerializedName("Ischeck")
    @Expose
    public Boolean ischeck;
    @SerializedName("transactionId")
    @Expose
    public Object transactionId;
    @SerializedName("userId")
    @Expose
    public String userId;
    @SerializedName("ipAddress")
    @Expose
    public Object ipAddress;
    @SerializedName("IsReceipt")
    @Expose
    public Boolean isReceipt;
    @SerializedName("sURL")
    @Expose
    public Object sURL;
    @SerializedName("invoiceTemplateId")
    @Expose
    public String invoiceTemplateId;
    @SerializedName("EAmount")
    @Expose
    public Integer eAmount;
    @SerializedName("LAmount")
    @Expose
    public Integer lAmount;
    @SerializedName("TAmount")
    @Expose
    public Integer tAmount;
    @SerializedName("StudentInvoiceDetailsModelsData")
    @Expose
    public List<Object> studentInvoiceDetailsModelsData = null;
    @SerializedName("ListFeeHeads")
    @Expose
    public List<Object> listFeeHeads = null;
    @SerializedName("Listinvoices")
    @Expose
    public List<Object> listinvoices = null;
    @SerializedName("VSectionStudentAttendanceModelsList")
    @Expose
    public List<Object> vSectionStudentAttendanceModelsList = null;
    
}
