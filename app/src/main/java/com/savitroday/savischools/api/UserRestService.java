package com.savitroday.savischools.api;


import com.google.common.base.Strings;
import com.savitroday.savischools.api.response.Assignment;
import com.savitroday.savischools.api.response.Conversation;
import com.savitroday.savischools.api.response.Dashboard;
import com.savitroday.savischools.api.response.Invoice;
import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.api.response.MessageNotification;
import com.savitroday.savischools.api.response.ParentProfile;
import com.savitroday.savischools.api.response.Student;

import java.util.HashMap;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by harshitaahuja.
 */
public interface UserRestService {

    /* ================
        API
     ================ */
    @GET("api/parent/GetInvoiceByStudent")
    CustomCallAdapter.CustomCall<List<Invoice>> getInvoiceByStudent(@Query("schoolid") String schoolId, @Query("studntid")
            String studentId);

    @GET("api/parent/GetMessages")
    CustomCallAdapter.CustomCall<List<MessageNotification>> getMessages(@Query("schoolid") String schoolId, @Query("userId")
            String userID);

    @GET("api/parent/StudentProfileDetail")
    CustomCallAdapter.CustomCall<Dashboard> getDashboard(@Query("schoolid") String schoolId,
                                                         @Query("parentId") String parentId,
                                                         @Query("userId") String userId);


    @GET("api/parent/parentprofile")
    CustomCallAdapter.CustomCall<ParentProfile> getParentProfile(@Query("schoolId") String schoolId, @Query("parentId")
            String parentId);


    @GET("api/parent/studentprofile")
    CustomCallAdapter.CustomCall<Student> getStudentProfile(@Query("schoolid") String schoolId, @Query("studntid")
            String
            studentId);

    @POST("api/parent/getReadMessageStatus")
    CustomCallAdapter.CustomCall<MessageNotification> readStatusUpdate( @Body HashMap<String,String> mapv);

    @GET("api/parent/getstudentassignment")
    CustomCallAdapter.CustomCall<List<Assignment>> getStudentAssignment(@Query("schoolId") String schoolId, @Query
            ("studntId")
            String
            studentId);


    @POST("api/parent/deleteMessageNotification")
    CustomCallAdapter.CustomCall<MessageNotification> deleteMessageNotification(@Body HashMap<String,String> map);

    @GET("api/parent/GetMessageDetails")
    CustomCallAdapter.CustomCall<Conversation> getMessageConversation(@Query("schoolid") String schoolId,
                                                                      @Query("messageId") String messageId,
                                                                      @Query("isNotification") boolean
                                                                              isNotification);

    @POST("api/parent/Responseofmessages")
    CustomCallAdapter.CustomCall<List<Message>> replyToConversation(@Body Message message);


}