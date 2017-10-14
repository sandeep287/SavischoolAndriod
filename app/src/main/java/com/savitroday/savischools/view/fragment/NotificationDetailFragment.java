package com.savitroday.savischools.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.databinding.FragmentNotificationOpenViewBinding;

public class NotificationDetailFragment extends Fragment {
    
    Message message;
    FragmentNotificationOpenViewBinding mBindings;
    
    public static NotificationDetailFragment getInstance(Message message) {
        NotificationDetailFragment notificationOpenViewFra = new NotificationDetailFragment();
        notificationOpenViewFra.message = message;
        return notificationOpenViewFra;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBindings = DataBindingUtil.inflate(inflater, R.layout.fragment_notification_open_view, container, false);
        mBindings.setMessage(message);
        mBindings.setHandler(new Handler());
        return mBindings.getRoot();
    }
    public class Handler {
        public void onBackPressed() {
            getActivity().onBackPressed();
        }
    }
}
