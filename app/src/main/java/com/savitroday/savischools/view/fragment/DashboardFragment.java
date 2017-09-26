package com.savitroday.savischools.view.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.InvoiceAdapter;
import com.savitroday.savischools.adapter.MessageAdapter;
import com.savitroday.savischools.api.ApiException;
import com.savitroday.savischools.api.CustomCallAdapter;
import com.savitroday.savischools.api.UserRestService;
import com.savitroday.savischools.api.response.Dashboard;
import com.savitroday.savischools.databinding.FragmentDashboardBinding;
import com.savitroday.savischools.util.Constants;

import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {
    
    FragmentDashboardBinding mBindings;
    @Inject
    UserRestService userRestService;
    
    public DashboardFragment() {
        // Required empty public constructor
    }
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        
        mBindings = DataBindingUtil.inflate(
                inflater, R.layout.fragment_dashboard, container, false);
        MyApplication.getApp().getComponent().inject(this);
        
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.cell_header, mBindings.notificationListview, false);
        mBindings.notificationListview.addHeaderView(header, null, false);
        mBindings.invoiceListview.addHeaderView(header, null, false);
        getDashboardData();
        return mBindings.getRoot();
    }
    
    void getDashboardData() {
        mBindings.progressBar.setVisibility(View.VISIBLE);
        String parentId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_PARENT_ID);
        String schoolId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_SCHOOL_ID);
        HashMap<String, String> map = new HashMap<>();
        map.put("schoolid", schoolId);
        map.put("parentId", parentId);
        userRestService.getDashboard(map).enqueue(new CustomCallAdapter.CustomCallback<Dashboard>() {
            @Override
            public void success(Response<Dashboard> response) {
                Dashboard dashboard = response.body();
                mBindings.setDashboard(dashboard);
                InvoiceAdapter adapter = new InvoiceAdapter(getActivity(), dashboard.listStudentInvoiceModel);
                MessageAdapter messageAdapter = new MessageAdapter(getActivity(), dashboard.listSchoolMessagesModel);
                mBindings.notificationListview.setAdapter(messageAdapter);
                mBindings.invoiceListview.setAdapter(adapter);
                mBindings.progressBar.setVisibility(View.GONE);
            }
            
            @Override
            public void failure(ApiException e) {
                mBindings.progressBar.setVisibility(View.GONE);
            }
        });
    }
    
    public class Handler {
        public void onSelectChildren() {
            //TODO: show popup here
        }
    }
    
}
