package com.savitroday.savischools.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.MessageTabAdepter;
import com.savitroday.savischools.adapter.NotificationTabAdepter;
import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.databinding.FragmentNotificationBinding;
import com.savitroday.savischools.manager.NotificationManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NotificationFragment extends Fragment {
    RecyclerView notificationListView;
    List<Message> noyificationList = new ArrayList<>();

    RelativeLayout progressBar;
    FragmentNotificationBinding mBindings;
    NotificationTabAdepter notificationAdapter;
    @Inject
    NotificationManager notificationManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mBindings = DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false);

        MyApplication.getApp().getComponent().inject(this);

        notificationListView = mBindings.notificationList;

        LinearLayoutManager llm2 = new LinearLayoutManager(getActivity());
        llm2.setOrientation(LinearLayoutManager.VERTICAL);
        notificationListView.setLayoutManager(llm2);
        getMessageData();

        return mBindings.getRoot();
    }
    public void getMessageData() {
mBindings.progressBar.setVisibility(View.VISIBLE);
        notificationManager.getMessageTask().continueWith((task -> {
            mBindings.progressBar.setVisibility(View.GONE);
            if (task.getResult() != null) {
                noyificationList= NotificationManager.getNOtificationList();
                notificationAdapter = new  NotificationTabAdepter(getActivity(), noyificationList);
                notificationListView.setAdapter(notificationAdapter);

            } else {

            }


            return null;
        }));

    }
}
