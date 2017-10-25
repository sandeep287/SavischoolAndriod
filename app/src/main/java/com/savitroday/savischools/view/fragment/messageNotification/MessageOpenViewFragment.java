package com.savitroday.savischools.view.fragment.messageNotification;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
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
import com.savitroday.savischools.adapter.MessageOpenViewAdapter;
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
    MessageOpenViewAdapter adapter;

    public static MessageOpenViewFragment getInstance(MessageNotification messageNotification) {
        MessageOpenViewFragment messageOpenViewFragment = new MessageOpenViewFragment();
        messageOpenViewFragment.messageNotification = messageNotification;
        return messageOpenViewFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        messageNotifications = new ArrayList<>();
        mBindings = DataBindingUtil.inflate(inflater, R.layout.fragment_message_open_view, container, false);
        mBindings.setHandler(new Handler());
        MyApplication.getApp().getComponent().inject(this);
        messaglist = mBindings.reyclerviewMessageList;
        MyApplication.getApp().getComponent().inject(this);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        messaglist.setLayoutManager(llm);
        // messages.add(message);
        if (messageNotification.isViewed==false) {
            setStatus();
        }
        getMessageData();

        return mBindings.getRoot();
    }

    public void getMessageData() {
        mBindings.progressBar.setVisibility(View.VISIBLE);

        notificationManager.getMessageConversation(messageNotification.schoolMessageId).continueWith((task -> {
            mBindings.progressBar.setVisibility(View.GONE);

            if (task.getResult() != null) {
                conversation = (Conversation) task.getResult();
                messageNotifications = conversation.messageList;
                adapter = new MessageOpenViewAdapter(getActivity(), messageNotifications, messageNotification);
                messaglist.setAdapter(adapter);
            }
            else {

            }


            return null;
        }));

    }

    public void deleteMessageData() {

        mBindings.progressBar.setVisibility(View.VISIBLE);
        notificationManager.deleteMessageNotification(messageNotification.schoolMessageId).continueWith((task -> {
            mBindings.progressBar.setVisibility(View.GONE);


            if (task.getResult() != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                builder.setMessage("Deleted successfully...");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().onBackPressed();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            } else {
                Exception e = task.getError();
                AlertUtil.showSnackbarWithMessage(e.getMessage(), mBindings.getRoot());
            }


            return null;
        }));

    }

    public void setMessageData() {
        Message message = new Message();
        message.schoolMessageId = messageNotification.schoolMessageId;
        message.message = mBindings.edittextChatbox.getText().toString().trim();
        message.userId = Constants.SHARED_PREFERENCES_USER_ID;
        message.parentId = Constants.SHARED_PREFERENCES_PARENT_ID;
        mBindings.progressBar.setVisibility(View.VISIBLE);
        notificationManager.replyToConversation(message).continueWith((task -> {
            mBindings.progressBar.setVisibility(View.GONE);
            if (task.getResult() != null) {
                List<Message> messages = (List<Message>) task.getResult();
                // messageNotifications.clear();
                //adapter.setItems(messages);
                MessageOpenViewAdapter adapterr = new MessageOpenViewAdapter(getActivity(), messages, messageNotification);
                mBindings.reyclerviewMessageList.setAdapter(adapterr);
                //adapter.notifyDataSetChanged();
                mBindings.edittextChatbox.setText("");

            } else {

            }


            return null;
        }));
    }

    public class Handler {
        public void onBackPressed() {
            getActivity().onBackPressed();
        }

        public void onDeletePress() {


            // deleteMessageData();
            getActivity().onBackPressed();
        }

        public void onSendPress()
        {

            if (!(mBindings.edittextChatbox.getText().toString().trim().equals(""))) {

                setMessageData();
            }
        }
    }
    public void setStatus() {
        notificationManager.readStatusUpdate(messageNotification.schoolMessageId).continueWith((task -> {
            if (task.getResult() != null) {
                Log.e("status","1");

            } else {
                Log.e("status","2");
                Exception e = task.getError();
                AlertUtil.showSnackbarWithMessage(e.getMessage(), mBindings.getRoot());
            }
            return null;
        }));
    }

}
