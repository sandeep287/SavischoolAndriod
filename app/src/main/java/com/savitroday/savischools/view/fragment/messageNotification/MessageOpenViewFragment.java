package com.savitroday.savischools.view.fragment.messageNotification;

import android.databinding.DataBindingUtil;
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
import com.savitroday.savischools.api.response.Conversation;
import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.api.response.MessageNotification;
import com.savitroday.savischools.databinding.FragmentMessageOpenViewBinding;
import com.savitroday.savischools.manager.NotificationManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class MessageOpenViewFragment extends Fragment {

    NotificationManager messageManager;
    RecyclerView messaglist;
    MessageNotification messageNotification;
    Conversation conversation;
    @Inject
    NotificationManager notificationManager;
    FragmentMessageOpenViewBinding mBindings;
    List<Message> messageNotifications;
    MessageOpenViewAdepter adepter;

    public static MessageOpenViewFragment getInstance(MessageNotification messageNotification) {
        MessageOpenViewFragment messageOpenViewFragment = new MessageOpenViewFragment();
        messageOpenViewFragment.messageNotification = messageNotification;
        return messageOpenViewFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

         messageNotifications =new ArrayList<>();
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

        notificationManager.getMessageConversation(messageNotification.schoolMessageId).continueWith((task -> {


            if (task.getResult() != null) {
                conversation  =(Conversation) task.getResult();
                messageNotifications=conversation.messageList;

                 adepter =new MessageOpenViewAdepter(getActivity(), messageNotifications, messageNotification);
                messaglist.setAdapter(adepter);

            } else {

            }


            return null;
        }));

    }
    public void deleteMessageData() {

        notificationManager.deleteMessageNotification(messageNotification.schoolMessageId).continueWith((task -> {


            if (task.getResult() != null) {


            } else {

            }


            return null;
        }));

    }
    public  void  setMessageData()
    {

    }
    public class Handler {
        public void onBackPressed()
        {
            getActivity().onBackPressed();
        }
        public  void onDeletePress()
               {
            deleteMessageData(); 
               }
        public  void onSendPress()
               {

               }
    }

}
