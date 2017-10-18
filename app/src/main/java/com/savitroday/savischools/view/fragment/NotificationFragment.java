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
import com.savitroday.savischools.adapter.NotificationAdapter;
import com.savitroday.savischools.api.response.MessageNotification;
import com.savitroday.savischools.databinding.FragmentNotificationBinding;
import com.savitroday.savischools.manager.NotificationManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NotificationFragment extends Fragment {
    List<MessageNotification> notificationList = new ArrayList<>();
    FragmentNotificationBinding mBindings;
    NotificationAdapter notificationAdapter;
    @Inject
    NotificationManager notificationManager;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        
        mBindings = DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false);
        
        MyApplication.getApp().getComponent().inject(this);
        LinearLayoutManager llm2 = new LinearLayoutManager(getActivity());
        llm2.setOrientation(LinearLayoutManager.VERTICAL);
        mBindings.notificationList.setLayoutManager(llm2);
        getMessageData();
        
        return mBindings.getRoot();
    }
    
    public void getMessageData() {
        mBindings.progressBar.setVisibility(View.VISIBLE);
        notificationManager.getMessageTask().continueWith((task -> {
            mBindings.progressBar.setVisibility(View.GONE);
            if (task.getResult() != null) {
                notificationList = NotificationManager.getNOtificationList();
                notificationAdapter = new NotificationAdapter(getActivity(), notificationList);
                mBindings.notificationList.setAdapter(notificationAdapter);
                
            } else {
                
            }
            return null;
        }));
        
    }
}
