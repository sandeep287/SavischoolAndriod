package com.savitroday.savischools.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.ChildrenNameAdepter;
import com.savitroday.savischools.api.response.Dashboard;
import com.savitroday.savischools.api.response.ParentProfile;
import com.savitroday.savischools.api.response.Student;
import com.savitroday.savischools.databinding.FragmentParentProfileBinding;
import com.savitroday.savischools.manager.DashboardManager;
import com.savitroday.savischools.manager.MyProfileManager;
import com.savitroday.savischools.util.AlertUtil;
import com.savitroday.savischools.util.EventManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class ParentProfileFragment extends Fragment implements EventManager.EventManagerDelegate {
    
    FragmentParentProfileBinding mBindings;
    @Inject
    DashboardManager dashboardManager;
    RecyclerView childrenlist;
    List<Student> students;
    @Inject
    MyProfileManager myProfileManager;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBindings = DataBindingUtil.inflate(inflater, R.layout.fragment_parent_profile, container, false);
        mBindings.setHandler(new Handler());
        childrenlist = mBindings.childrenlist;
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        childrenlist.setLayoutManager(llm);
        MyApplication.getApp().getComponent().inject(this);
        getParentData();
        getStudentListData();
        ChildrenNameAdepter adepter = new ChildrenNameAdepter(getActivity(), students);
        childrenlist.setAdapter(adepter);
        return mBindings.getRoot();
        
    }
    
    public ParentProfileFragment() {
    }
    
    public void getParentData() {
        mBindings.progressBar.setVisibility(View.VISIBLE);
        
        myProfileManager.getMyProfileTask().continueWith((task -> {
            mBindings.progressBar.setVisibility(View.INVISIBLE);
            if (task.getResult() != null) {
                mBindings.setParent((ParentProfile) task.getResult());
                //todo : set image using piccasso
            } else {
                Exception e = task.getError();
                AlertUtil.showSnackbarWithMessage(e.getMessage(), mBindings.getRoot());
            }
            return null;
        }));
        
    }
    
    void getStudentListData() {
        
        
        dashboardManager.getDashboardTask().continueWith((task -> {
            
            if (task.getResult() != null) {
                Dashboard dashboard = (Dashboard) task.getResult();
                students = dashboard.listStudentModel;
            } else {
                Exception e = task.getError();
                AlertUtil.showSnackbarWithMessage(e.getMessage(), mBindings.getRoot());
            }
            
            return null;
        }));
    }
    
    @Override
    public void didReceivedEvent(int id, Object... args) {
        getParentData();
    }
    
    public class Handler {
        public void onNotificationTap() {
            Fragment fragment = new NotificationMessageTabFragment();
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.flFragments, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        
        public void onBackPressed() {
            getActivity().onBackPressed();
        }
    }
}
