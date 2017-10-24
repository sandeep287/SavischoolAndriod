package com.savitroday.savischools.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.MessageNotification;
import com.savitroday.savischools.databinding.NotificationCellBinding;
import com.savitroday.savischools.manager.NotificationManager;
import com.savitroday.savischools.view.fragment.messageNotification.NotificationDetailFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by harshitaahuja on 9/1/2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    
    Context context;
    List<MessageNotification> list;
    LayoutInflater layoutInflater;
    @Inject
    NotificationManager notificationManager;
    
    public class MyViewHolder extends RecyclerView.ViewHolder {
        
        NotificationCellBinding mBinding;
        
        public MyViewHolder(NotificationCellBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }
    
    public NotificationAdapter(Activity context, List<MessageNotification> list) {
        this.list = list;
        this.context = context;
        Log.e("list_sizzzzzzzzzzzze",""+list.size());
        this.layoutInflater = (LayoutInflater)
                                      context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        MyApplication.getApp().getComponent().inject(this);
    }
    
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NotificationCellBinding binding = NotificationCellBinding.inflate(layoutInflater,
                parent, false);
        Log.e("list_sizzzzzzzzzzzze",""+list.size());
        return new MyViewHolder(binding);
    }
    
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mBinding.setMessage(list.get(position));
        holder.mBinding.setHandler(new Handler());
        Log.e("list_sizzzzzzzzzzzze",""+list.size());
        if ((list.get(position)).iconMediaPath != null) {
            Picasso.with(context).load((list.get(position)).iconMediaPath).into(holder.mBinding.imageView);
            holder.mBinding.imageView.setPadding(0, 0, 0, 0);
        }
        if (list.get(position).studentName != null) {
            if ((!list.get(position).studentName.equals(""))) {
                holder.mBinding.studentName.setVisibility(View.VISIBLE);
            }
        }
    }
    
    @Override
    public int getItemCount() {

        return list.size();
    }
    
    public class Handler {
        public void openNotificationDetail(MessageNotification notification) {
            Fragment fragment = NotificationDetailFragment.getInstance(notification);
            FragmentManager manager = ((FragmentActivity) context).getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.flFragments, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        
        public void deleteNotification(MessageNotification notification)
        {
            notificationManager.deleteMessageNotification(notification.schoolMessageId).continueWith((task -> {



                return null;
            }));
            

        }
    }
}