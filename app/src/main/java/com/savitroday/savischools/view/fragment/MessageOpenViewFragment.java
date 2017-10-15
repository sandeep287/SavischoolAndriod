package com.savitroday.savischools.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.MessageOpenViewAdepter;
import com.savitroday.savischools.adapter.MessageTabAdepter;
import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.databinding.FragmentMessageOpenViewBinding;
import com.savitroday.savischools.databinding.FragmentMessagesBinding;
import com.savitroday.savischools.manager.NotificationManager;
import com.savitroday.savischools.util.AlertUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;


public class MessageOpenViewFragment extends Fragment {

    NotificationManager messageManager;
    RecyclerView messaglist;
    Message message;
    @Inject
    NotificationManager notificationManager;
    FragmentMessageOpenViewBinding mBindings;
    List<Message> messages;
    MessageOpenViewAdepter adepter;

    public static MessageOpenViewFragment getInstance(Message message) {
        MessageOpenViewFragment messageOpenViewFragment = new MessageOpenViewFragment();
        messageOpenViewFragment.message = message;
        return messageOpenViewFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        messages=new ArrayList<>();

        mBindings = DataBindingUtil.inflate(inflater, R.layout.fragment_message_open_view, container, false);
        MyApplication.getApp().getComponent().inject(this);
        messaglist=mBindings.reyclerviewMessageList;
        MyApplication.getApp().getComponent().inject(this);
        LinearLayoutManager llm=new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        messaglist.setLayoutManager(llm);
       // messages.add(message);
        getMessageData();

        return mBindings.getRoot();
    }
    public void getMessageData() {

        notificationManager.getMessageTask().continueWith((task -> {

            if (task.getResult() != null) {
                messages=NotificationManager.getComunicationwith(message.senderName);
                 adepter =new MessageOpenViewAdepter(getActivity(),messages,message);
                messaglist.setAdapter(adepter);

            } else {

            }


            return null;
        }));

    }
    public class Handler { public void onBackPressed() {
            getActivity().onBackPressed();
        }
    }

}
