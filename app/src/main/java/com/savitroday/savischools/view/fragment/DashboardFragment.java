package com.savitroday.savischools.view.fragment;


import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.savitroday.savischools.manager.DashboardManager;
import com.savitroday.savischools.util.AlertUtil;
import com.savitroday.savischools.util.Constants;
import com.savitroday.savischools.view.activity.MainActivity;

import org.eclipse.jdt.internal.compiler.batch.Main;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {
    
    FragmentDashboardBinding mBindings;
   
    @Inject
    DashboardManager dashboardManager;
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
        
        dashboardManager.getDashboardTask().continueWith((task -> {
            mBindings.progressBar.setVisibility(View.GONE);
            if(task.getResult() != null){
                dashboard =(Dashboard) task.getResult();
                mBindings.setDashboard(dashboard);
                mBindings.setHandler(new Handler());
                dashboardAdapter = new DashboardAdapter(getContext(), dashboard.listStudentInvoiceModel,
                                                               dashboard.listSchoolMessagesModel);
                mBindings.listView.setAdapter(dashboardAdapter);
                mBindings.listView.expandGroup(0);
                mBindings.listView.expandGroup(1);
                setNavDrawer();
            }
            else
            {
                Exception e = task.getError();
                AlertUtil.showSnackbarWithMessage(e.getMessage(),mBindings.getRoot());
            }
            
            return null;
        }));
    }
    
    void setNavDrawer(){
        ((MainActivity)getActivity()).setNavigationList(dashboard.listStudentModel);
    }
    
    public class Handler {
        public void onProfileTap() {
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.flFragments, ProfileFragment.getInstance(dashboard.getDefaultStudent()));
            transaction.commit();
        }
    }
    
    
}
