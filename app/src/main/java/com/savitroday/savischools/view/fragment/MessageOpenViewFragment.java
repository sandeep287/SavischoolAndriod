package com.savitroday.savischools.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.MessageOpenViewAdepter;
import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.manager.NotificationManager;
import com.savitroday.savischools.util.AlertUtil;

import java.util.List;

import javax.inject.Inject;


public class MessageOpenViewFragment extends Fragment {
View view;
    NotificationManager messageManager;
    RecyclerView messaglist;
    Message message;
    List<Message> messages;

    public static MessageOpenViewFragment getInstance(Message message) {
        MessageOpenViewFragment messageOpenViewFragment = new MessageOpenViewFragment();
        messageOpenViewFragment.message = message;
        return messageOpenViewFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        view= inflater.inflate(R.layout.fragment_message_open_view, container, false);
        messaglist=(RecyclerView)view.findViewById(R.id.reyclerview_message_list);
        MyApplication.getApp().getComponent().inject(this);
        LinearLayoutManager llm=new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        messaglist.setLayoutManager(llm);
        //messages=
        MessageOpenViewAdepter adepter=new MessageOpenViewAdepter(getActivity(),messages);
        messaglist.setAdapter(adepter);
        return view;
    }

}
