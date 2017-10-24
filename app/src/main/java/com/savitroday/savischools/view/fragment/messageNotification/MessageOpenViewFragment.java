package com.savitroday.savischools.view.fragment.messageNotification;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.MessageOpenViewAdepter;
import com.savitroday.savischools.api.response.Conversation;
import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.api.response.MessageNotification;
import com.savitroday.savischools.databinding.FragmentMessageOpenViewBinding;
import com.savitroday.savischools.manager.NotificationManager;
import com.savitroday.savischools.util.AlertUtil;
import com.savitroday.savischools.util.Constants;
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
        mBindings.setHandler(new Handler());
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
        mBindings.progressBar.setVisibility(View.VISIBLE);

        notificationManager.getMessageConversation(messageNotification.schoolMessageId).continueWith((task -> {
          mBindings.progressBar.setVisibility(View.GONE);

            if (task.getResult() != null) {
                conversation  =(Conversation) task.getResult();
                messageNotifications=conversation.messageList;
                Log.e("chackkkkkkkkkkkkkkkkk",""+messageNotifications.size());
                 adepter =new MessageOpenViewAdepter(getActivity(), messageNotifications, messageNotification);
                messaglist.setAdapter(adepter);

            } else {

            }


            return null;
        }));

    }
    public void deleteMessageData() {

        mBindings.progressBar.setVisibility(View.VISIBLE);
        notificationManager.deleteMessageNotification(messageNotification.schoolMessageId).continueWith((task -> {
        mBindings.progressBar.setVisibility(View.GONE);
            Toast toast=Toast.makeText(getActivity(),"Deleted successfully...",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);

            if (task.getResult() != null)
            {
                toast.show();

            }
            else {
                Exception e = task.getError();
                AlertUtil.showSnackbarWithMessage(e.getMessage(), mBindings.getRoot());
            }


            return null;
        }));

    }
   public  void  setMessageData()
    {
        Message message=new Message();
        message.schoolMessageId=messageNotification.schoolMessageId;
        message.message=mBindings.edittextChatbox.getText().toString().trim();
        message.userId= Constants.SHARED_PREFERENCES_USER_ID;
        message.parentId=Constants.SHARED_PREFERENCES_PARENT_ID;
        mBindings.progressBar.setVisibility(View.VISIBLE);
        notificationManager.replyToConversation(message).continueWith((task -> {
            mBindings.progressBar.setVisibility(View.GONE);
            if (task.getResult() != null) {
               //adepter.addmsg((List<Message>)task.getResult());
                List<Message> messages=(List<Message>)task.getResult();
               messageNotifications.add(messages.get(messages.size()-1));

                adepter.notifyDataSetChanged();
                mBindings.edittextChatbox.setText("");
        // MessageOpenViewAdepter adepter=new MessageOpenViewAdepter(getActivity(),(List<Message>)task.getResult(),messageNotification);


            } else {

            }


            return null;
        }));
    }
    public class Handler {
        public void onBackPressed()
        {
            getActivity().onBackPressed();
        }
        public  void onDeletePress()
               {


                  // deleteMessageData();
                   getActivity().onBackPressed();
               }
        public  void onSendPress()
               {

                 if(!(mBindings.edittextChatbox.getText().toString().trim().equals("")))
                    {

                         setMessageData();
                     }
               }
    }

}
