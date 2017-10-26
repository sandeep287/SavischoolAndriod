package com.savitroday.savischools.view.fragment.messageNotification;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.MessageAdapter;
import com.savitroday.savischools.api.response.MessageNotification;
import com.savitroday.savischools.databinding.FragmentMessagesBinding;
import com.savitroday.savischools.manager.NotificationManager;
import com.savitroday.savischools.util.AlertUtil;
import com.savitroday.savischools.util.Event;
import com.savitroday.savischools.util.EventManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MessagesFragment extends Fragment implements EventManager.EventManagerDelegate {
    
    
    List<MessageNotification> messageNotificationList = new ArrayList<>();
    MessageAdapter messageAdapter;
    @Inject
    NotificationManager notificationManager;
    FragmentMessagesBinding mBindings;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        mBindings = DataBindingUtil.inflate(inflater, R.layout.fragment_messages, container, false);
        MyApplication.getApp().getComponent().inject(this);
        
        EventManager.getInstance().addObserver(this, Event.MESSAGES_UPDATED);
        LinearLayoutManager llm2 = new LinearLayoutManager(getActivity());
        llm2.setOrientation(LinearLayoutManager.VERTICAL);
        mBindings.messageList.setLayoutManager(llm2);
        getMessageData();
        
        return mBindings.getRoot();
    }
    
    public void getMessageData() {
        mBindings.progressBar.setVisibility(View.VISIBLE);
        notificationManager.getMessageTask().continueWith((task -> {
            mBindings.progressBar.setVisibility(View.GONE);
            if (task.getResult() != null) {
                messageNotificationList = NotificationManager.getMessageNotificationList();
                messageAdapter = new MessageAdapter(getActivity(), messageNotificationList);
                mBindings.messageList.setAdapter(messageAdapter);
                
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
        EventManager.getInstance().removeObserver(this, Event.MESSAGES_UPDATED);
    }
}
