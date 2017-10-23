package com.savitroday.savischools.view.fragment.messageNotification;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.MessageNotification;
import com.savitroday.savischools.databinding.FragmentNotificationOpenViewBinding;
import com.savitroday.savischools.manager.NotificationManager;

import javax.inject.Inject;

public class NotificationDetailFragment extends Fragment {
    
    MessageNotification messageNotification;
    FragmentNotificationOpenViewBinding mBindings;
    @Inject
    NotificationManager notificationManager;
    
    public static NotificationDetailFragment getInstance(MessageNotification messageNotification) {
        NotificationDetailFragment notificationOpenViewFra = new NotificationDetailFragment();
        notificationOpenViewFra.messageNotification = messageNotification;
        return notificationOpenViewFra;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBindings = DataBindingUtil.inflate(inflater, R.layout.fragment_notification_open_view, container, false);
        MyApplication.getApp().getComponent().inject(this);
        mBindings.setMessage(messageNotification);
        mBindings.setHandler(new Handler());
        
        //todo: call readmessage task here
        
        return mBindings.getRoot();
    }
    public void deleteMessageData() {

        notificationManager.deleteMessageNotification(messageNotification.schoolMessageId).continueWith((task -> {


            if (task.getResult() != null) {


            } else {

            }


            return null;
        }));

    }
    public class Handler {
        public void onBackPressed() {
            getActivity().onBackPressed();
        }
        public  void onDeletePress()
        {
            deleteMessageData();


        }
    }
}
