package com.savitroday.savischools.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.ChildrenNameAdapter;
import com.savitroday.savischools.api.response.Dashboard;
import com.savitroday.savischools.api.response.Student;
import com.savitroday.savischools.databinding.FragmentParentProfileBinding;
import com.savitroday.savischools.manager.DashboardManager;
import com.savitroday.savischools.util.AlertUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ParentProfileFragment extends Fragment {
    
    FragmentParentProfileBinding mBindings;
    @Inject
    DashboardManager dashboardManager;
    List<Student> students = new ArrayList<>();
    ChildrenNameAdapter adapter;
    
    
    public ParentProfileFragment() {
    }
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBindings = DataBindingUtil.inflate(inflater, R.layout.fragment_parent_profile, container, false);
        MyApplication.getApp().getComponent().inject(this);
        
        mBindings.setHandler(new Handler());
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mBindings.childrenlist.setLayoutManager(llm);
        
        getStudentListData();
        
        return mBindings.getRoot();
        
    }
    
    void setBadgeCount(int count) {
        if (count > 0) {
            mBindings.notificationBadge.setText("" + count);
            mBindings.notificationBadge.setVisibility(View.VISIBLE);
        } else {
            mBindings.notificationBadge.setVisibility(View.GONE);
        }
    }
    
    void getStudentListData() {
        dashboardManager.getDashboardTask().continueWith((task -> {
            
            if (task.getResult() != null) {
                Dashboard dashboard = (Dashboard) task.getResult();
                mBindings.setParent(dashboard.getParentProfile());
                students = dashboard.listStudentModel;
                adapter = new ChildrenNameAdapter(getActivity(), students);
                mBindings.childrenlist.setAdapter(adapter);
                setBadgeCount(dashboard.unreadMessagesNotification);
            } else {
                Exception e = task.getError();
                AlertUtil.showSnackbarWithMessage(e.getMessage(), mBindings.getRoot());
            }
            
            return null;
        }));
    }
    
    public class Handler {
        public void onNotificationTap() {

//            Fragment fragment = new NotificationMessageTabFragment();
//            FragmentManager manager = getActivity().getSupportFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.add(R.id.flFragments, fragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
        }
        
        public void onBackPressed() {
            getActivity().onBackPressed();
        }
    }
}
