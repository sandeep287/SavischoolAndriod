package com.savitroday.savischools.api;



import com.savitroday.savischools.api.response.Dashboard;
import com.savitroday.savischools.api.response.Invoice;

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
    CustomCallAdapter.CustomCall<String> getMessages(@Query("schoolid") String schoolId,@Query("studntid")
                                                                                                     String studentId);
    
    @POST("api/parent/StudentProfileDetail")
    CustomCallAdapter.CustomCall<Dashboard> getDashboard(@Body HashMap map);
                                                                 //("schoolid") String schoolId, @Query("parentId")
                                                                                            // String parentId);
    
}
