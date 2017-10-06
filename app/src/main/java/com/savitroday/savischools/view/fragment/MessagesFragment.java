package com.savitroday.savischools.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.MessageNotificationAdepter;
import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.manager.NotificationManager;
import com.savitroday.savischools.util.AlertUtil;
import com.savitroday.savischools.util.EventManager;

import java.util.List;

import javax.inject.Inject;

public class MessagesFragment extends Fragment  implements EventManager.EventManagerDelegate
        {

    RecyclerView messageList;
    List<Message> messages;
    Message message;
    View view;
    @Inject
    NotificationManager notificationManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_messages, container, false);

        MyApplication.getApp().getComponent().inject(this);
        Log.e("chack","a");
        adddata();
        messageList=(RecyclerView)view.findViewById(R.id.messageList);
        LinearLayoutManager llm=new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        messageList.setLayoutManager(llm);
        MessageNotificationAdepter adepter=new MessageNotificationAdepter(getActivity(),messages);
        messageList.setAdapter(adepter);
        return view;
    }
    void adddata()
    {
        notificationManager.getMessageTask().continueWith((task -> {

            if (task.getResult() != null) {
                Log.e("chack","c ");
                messages=notificationManager.getMessageList();

            } else {
                Log.e("chack","b ");
                Exception e = task.getError();
                AlertUtil.showSnackbarWithMessage(e.getMessage(), view);
            }

            return null;
        }));

    }

            @Override
            public void didReceivedEvent(int id, Object... args) {
                adddata();
            }
        }
