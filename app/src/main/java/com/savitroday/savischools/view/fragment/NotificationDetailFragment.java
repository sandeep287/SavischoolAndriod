package com.savitroday.savischools.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.MessageNotification;
import com.savitroday.savischools.databinding.FragmentNotificationOpenViewBinding;

public class NotificationDetailFragment extends Fragment {
    
    MessageNotification messageNotification;
    FragmentNotificationOpenViewBinding mBindings;
    
    public static NotificationDetailFragment getInstance(MessageNotification messageNotification) {
        NotificationDetailFragment notificationOpenViewFra = new NotificationDetailFragment();
        notificationOpenViewFra.messageNotification = messageNotification;
        return notificationOpenViewFra;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBindings = DataBindingUtil.inflate(inflater, R.layout.fragment_notification_open_view, container, false);
        mBindings.setMessage(messageNotification);
        mBindings.setHandler(new Handler());
        return mBindings.getRoot();
    }
    public class Handler {
        public void onBackPressed() {
            getActivity().onBackPressed();
        }
    }
}
