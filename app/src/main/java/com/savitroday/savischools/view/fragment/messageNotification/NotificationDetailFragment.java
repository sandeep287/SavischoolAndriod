package com.savitroday.savischools.view.fragment.messageNotification;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        
        if (messageNotification.isViewed == false) {
            setStatus();
        }
        return mBindings.getRoot();
    }
    
    public void deleteMessageData() {
        
        mBindings.progressBar.setVisibility(View.VISIBLE);
        notificationManager.deleteMessageNotification(messageNotification.schoolMessageId).continueWith((task -> {
            mBindings.progressBar.setVisibility(View.GONE);
            if (task.getResult() != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                builder.setMessage("Notiifcation deleted successfully");
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
    
    public void setStatus() {
        notificationManager.readStatusUpdate(messageNotification.schoolMessageId).continueWith((task -> {
            if (task.getResult() != null) {
                Log.e("status", "1");
                
            } else {
                Log.e("status", "2");
                Exception e = task.getError();
               // AlertUtil.showSnackbarWithMessage(e.getMessage(), mBindings.getRoot());
            }
            return null;
        }));
    }
    
    public class Handler {
        public void onBackPressed() {
            getActivity().onBackPressed();
        }
        
        public void onDeletePress() {
            deleteMessageData();
            
            
        }
    }
}
