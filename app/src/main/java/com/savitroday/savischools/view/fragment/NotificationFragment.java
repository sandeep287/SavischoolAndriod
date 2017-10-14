package com.savitroday.savischools.view.fragment;

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
import com.savitroday.savischools.manager.NotificationManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NotificationFragment extends Fragment {
    RecyclerView notificationListView;
    List<Message> noyificationList = new ArrayList<>();
    View view;
    RelativeLayout progressBar;
    NotificationTabAdepter notificationAdapter;
    @Inject
    NotificationManager notificationManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_notification, container, false);
        MyApplication.getApp().getComponent().inject(this);
        progressBar = (RelativeLayout) view.findViewById(R.id.progressBar);

        notificationListView = (RecyclerView) view.findViewById(R.id.notification_list);

        LinearLayoutManager llm2 = new LinearLayoutManager(getActivity());
        llm2.setOrientation(LinearLayoutManager.VERTICAL);
        notificationListView.setLayoutManager(llm2);
        getMessageData();

        return view;
    }
    public void getMessageData() {

        notificationManager.getMessageTask().continueWith((task -> {
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
