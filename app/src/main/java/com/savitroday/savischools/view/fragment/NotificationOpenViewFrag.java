package com.savitroday.savischools.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.databinding.FragmentNotificationOpenViewBinding;

public class NotificationOpenViewFrag extends Fragment {
    Message message;
  FragmentNotificationOpenViewBinding  mBindings;
    public static NotificationOpenViewFrag getInstance(Message message){
        NotificationOpenViewFrag notificationOpenViewFra = new NotificationOpenViewFrag();
        notificationOpenViewFra.message = message;
        return notificationOpenViewFra;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBindings =  DataBindingUtil.inflate(inflater,R.layout.fragment_notification_open_view,container,false);
        mBindings.setMessage(message);
        return mBindings.getRoot();
    }

}
