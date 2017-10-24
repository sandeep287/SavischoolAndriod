package com.savitroday.savischools.view.fragment.messageNotification;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.MessageNotification;
import com.savitroday.savischools.databinding.FragmentNotificationOpenViewBinding;
import com.savitroday.savischools.manager.NotificationManager;
import com.savitroday.savischools.util.AlertUtil;

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

        mBindings.setHandler(new Handler());
        MyApplication.getApp().getComponent().inject(this);
        mBindings.setMessage(messageNotification);
        mBindings.setHandler(new Handler());
        
        //todo: call readmessage task here
        setStatus();
        return mBindings.getRoot();
    }
    
    public void deleteMessageData() {

        mBindings.progressBar.setVisibility(View.VISIBLE);
        notificationManager.deleteMessageNotification(messageNotification.schoolMessageId).continueWith((task -> {
            mBindings.progressBar.setVisibility(View.GONE);
            Toast toast=Toast.makeText(getActivity(),"Deleted successfully...",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);

            if (task.getResult() != null) {
                toast.show();
                
            } else {
                Exception e = task.getError();
                AlertUtil.showSnackbarWithMessage(e.getMessage(), mBindings.getRoot());
            }
            
            
            return null;
        }));
        
    }
    public void setStatus()
    {
        notificationManager.readStatusUpdate(messageNotification.schoolMessageId).continueWith((task -> {
            if (task.getResult() != null) {
                Log.e("updattttttttttttttt","status");

            } else {
                Log.e("2updattttttttttttttt","status");
                Exception e = task.getError();
                AlertUtil.showSnackbarWithMessage(e.getMessage(), mBindings.getRoot());
            }
            return null;
        }));
    }
    
    public class Handler {
        public void onBackPressed() {
            getActivity().onBackPressed();
        }
        
        public void onDeletePress() {
         //   deleteMessageData();
           getActivity().onBackPressed();
        }
    }
}
