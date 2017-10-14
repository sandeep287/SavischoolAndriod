package com.savitroday.savischools.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.MessageTabAdepter;
import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.databinding.FragmentMessagesBinding;
import com.savitroday.savischools.databinding.FragmentNotificationBinding;
import com.savitroday.savischools.manager.NotificationManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MessagesFragment extends Fragment {
    
    RecyclerView messagesListView;
    List<Message> messageList = new ArrayList<>();

    RelativeLayout progressBar;
    MessageTabAdepter notificationAdapter;
    @Inject
    NotificationManager notificationManager;
    FragmentMessagesBinding mBindings;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBindings = DataBindingUtil.inflate(inflater, R.layout.fragment_messages, container, false);
        MyApplication.getApp().getComponent().inject(this);

        
        messagesListView = mBindings.messageList;
        
        LinearLayoutManager llm2 = new LinearLayoutManager(getActivity());
        llm2.setOrientation(LinearLayoutManager.VERTICAL);
        messagesListView.setLayoutManager(llm2);
        getMessageData();
        
        return mBindings.getRoot();
    }
    
    public void getMessageData() {
        mBindings.progressBar.setVisibility(View.VISIBLE);
        notificationManager.getMessageTask().continueWith((task -> {
            mBindings.progressBar.setVisibility(View.GONE);
            if (task.getResult() != null) {
                messageList = NotificationManager.getMessageList();
                notificationAdapter = new MessageTabAdepter(getActivity(), messageList);
                messagesListView.setAdapter(notificationAdapter);
                
            } else {
                
            }
            
            
            return null;
        }));
        
    }
    
    
}
