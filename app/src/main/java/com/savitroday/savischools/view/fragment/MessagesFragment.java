package com.savitroday.savischools.view.fragment;

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
import com.savitroday.savischools.adapter.NotificationAdapter;
import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.manager.NotificationManager;
import com.savitroday.savischools.util.AlertUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MessagesFragment extends Fragment {
    
    RecyclerView messagesListView;

    @Inject
    NotificationManager notificationManager;
    List<Message> messageList = new ArrayList<>();
    View view;
    RelativeLayout progressBar;
    NotificationAdapter notificationAdapter;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_messages, container, false);
        progressBar = (RelativeLayout) view.findViewById(R.id.progressBar);
        MyApplication.getApp().getComponent().inject(this);
        messagesListView = (RecyclerView) view.findViewById(R.id.message_list);
        
        LinearLayoutManager llm2 = new LinearLayoutManager(getActivity());
        llm2.setOrientation(LinearLayoutManager.VERTICAL);
        messagesListView.setLayoutManager(llm2);
        
        
        notificationAdapter = new NotificationAdapter(getActivity(), messageList);
        messagesListView.setAdapter(notificationAdapter);
        
        getInvoiceData();
        return view;
    }
    
    public void getInvoiceData() {
        progressBar.setVisibility(View.VISIBLE);
        
        notificationManager.getMessageTask().continueWith((task -> {
            progressBar.setVisibility(View.INVISIBLE);
            if (task.getResult() != null) {
                messageList = (List<Message>) task.getResult();
                notificationAdapter.notifyDataSetChanged();
            } else {
                Exception e = task.getError();
                AlertUtil.showSnackbarWithMessage(e.getMessage(), view);
            }
            return null;
        }));
        
    }
    
    
}
