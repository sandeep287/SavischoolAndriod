package com.savitroday.savischools.view.fragment.messageNotification;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.NotificationAdapter;
import com.savitroday.savischools.api.response.MessageNotification;
import com.savitroday.savischools.databinding.FragmentNotificationBinding;
import com.savitroday.savischools.manager.NotificationManager;
import com.savitroday.savischools.util.AlertUtil;
import com.savitroday.savischools.util.Event;
import com.savitroday.savischools.util.EventManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NotificationFragment extends Fragment implements EventManager.EventManagerDelegate {
    List<MessageNotification> notificationList = new ArrayList<>();
    FragmentNotificationBinding mBindings;
    NotificationAdapter notificationAdapter;
    public static RelativeLayout progressbar;
    @Inject
    NotificationManager notificationManager;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        
        mBindings = DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false);
        EventManager.getInstance().addObserver(this, Event.NOTIFICATION_UPDATED);
        progressbar = mBindings.progressBar;
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
                notificationAdapter.notifyDataSetChanged();
                
                
            } else {
                Exception e = task.getError();
                AlertUtil.callAlert(getActivity(), e.getMessage());
            }
            return null;
        }));
        
    }
    
    @Override
    public void didReceivedEvent(int id, Object... args) {
        getMessageData();
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventManager.getInstance().removeObserver(this, Event.NOTIFICATION_UPDATED);
    }
}