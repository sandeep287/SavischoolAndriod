package com.savitroday.savischools.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.PopupRecyclerviewAdepter;
import com.savitroday.savischools.api.ApiException;
import com.savitroday.savischools.api.CustomCallAdapter;
import com.savitroday.savischools.api.UserRestService;
import com.savitroday.savischools.api.response.Dashboard;
import com.savitroday.savischools.api.response.Invoice;
import com.savitroday.savischools.api.response.Student;
import com.savitroday.savischools.util.Constants;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * Created by owner on 9/26/2017.
 */

public class PopupStudentList extends Activity
{
    List<Student> list;
    @Inject
    UserRestService userRestService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectchildernpopup);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        getWindow().setLayout((int)( dm.widthPixels*.91),(int)(dm.heightPixels*.3));
        adddataList();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.stlist);

        LinearLayoutManager llm2 = new LinearLayoutManager(this);
        llm2.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm2);
        PopupRecyclerviewAdepter popupRecyclerviewAdepter=new PopupRecyclerviewAdepter(this,list);
        recyclerView.setAdapter(popupRecyclerviewAdepter);
    }
    private void adddataList()
    {
        String parentId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_PARENT_ID);
        String schoolId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_SCHOOL_ID);
        String userID = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_USER_ID);
     
        userRestService.getDashboard(schoolId,parentId,userID).enqueue(new CustomCallAdapter.CustomCallback<Dashboard>() {
        @Override
        public void success(Response<Dashboard> response) {
            Log.e("server",response.toString());
            Dashboard dashboard =  response.body();
            list=dashboard.listStudentModel;

        }

        @Override
        public void failure(ApiException e) {

        }
    });


    }
}