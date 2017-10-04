package com.savitroday.savischools.api.response;

/**
 * Created by Harshita Ahuja on 05/10/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Assignment {
    
    @SerializedName("schoolId")
    @Expose
    public Object schoolId;
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
    @SerializedName("curriculum")
    @Expose
    public String curriculum;
    @SerializedName("teacherComments")
    @Expose
    public String teacherComments;
    @SerializedName("remarks")
    @Expose
    public Object remarks;
    @SerializedName("submissionDate")
    @Expose
    public Object submissionDate;
    @SerializedName("mediaPath")
    @Expose
    public String mediaPath;
    @SerializedName("caption")
    @Expose
    public String caption;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("content")
    @Expose
    public String content;
    @SerializedName("classSectionId")
    @Expose
    public String classSectionId;
    @SerializedName("dueDate")
    @Expose
    public String dueDate;
    @SerializedName("rollNo")
    @Expose
    public Integer rollNo;
    @SerializedName("studentAssignmentId")
    @Expose
    public String studentAssignmentId;
    @SerializedName("classAssignmentId")
    @Expose
    public String classAssignmentId;
    @SerializedName("sectionStudentId")
    @Expose
    public String sectionStudentId;
    @SerializedName("studentcaption")
    @Expose
    public String studentcaption;
    @SerializedName("studentCommets")
    @Expose
    public String studentCommets;
    @SerializedName("status")
    @Expose
    public Object status;
    @SerializedName("studentId")
    @Expose
    public String studentId;
    @SerializedName("StudentmediaPath")
    @Expose
    public String studentmediaPath;
    
}


