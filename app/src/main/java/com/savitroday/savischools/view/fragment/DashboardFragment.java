package com.savitroday.savischools.view.fragment;


import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.DashboardAdapter;
import com.savitroday.savischools.api.ApiException;
import com.savitroday.savischools.api.CustomCallAdapter;
import com.savitroday.savischools.api.UserRestService;
import com.savitroday.savischools.api.response.Dashboard;
import com.savitroday.savischools.databinding.FragmentDashboardBinding;
import com.savitroday.savischools.util.AlertUtil;
import com.savitroday.savischools.util.Constants;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {
    
    FragmentDashboardBinding mBindings;
    @Inject
    UserRestService userRestService;
    Dashboard dashboard;
    DashboardAdapter dashboardAdapter;
    
    public DashboardFragment() {
        // Required empty public constructor
    }
    
    
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBindings = DataBindingUtil.inflate(
                inflater, R.layout.fragment_dashboard, container, false);
        MyApplication.getApp().getComponent().inject(this);
        
        getDashboardData();
        return mBindings.getRoot();
    }
    
    void getDashboardData() {
        mBindings.progressBar.setVisibility(View.VISIBLE);
        String parentId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_PARENT_ID);
        String schoolId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_SCHOOL_ID);
        String userID = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_USER_ID);
        
        userRestService.getDashboard(schoolId, parentId, userID).enqueue(new CustomCallAdapter
                                                                                     .CustomCallback<Dashboard>() {
            @Override
            public void success(Response<Dashboard> response) {
                dashboard = response.body();
                mBindings.setDashboard(dashboard);
                mBindings.setHandler(new Handler());
                mBindings.progressBar.setVisibility(View.GONE);
                dashboardAdapter = new DashboardAdapter(getContext(), dashboard.listStudentInvoiceModel,
                                                               dashboard.listSchoolMessagesModel);
                mBindings.listView.setAdapter(dashboardAdapter);
                mBindings.listView.expandGroup(0);
                mBindings.listView.expandGroup(1);
                
            }
            
            @Override
            public void failure(ApiException e) {
                mBindings.progressBar.setVisibility(View.GONE);
                AlertUtil.showSnackbarWithMessage(e.getMessage(), mBindings.getRoot());
            }
        });
    }
    
    public class Handler {
        public void onProfileTap() {
            //TODO: show popup here
            
        }
    }
    
    
}
