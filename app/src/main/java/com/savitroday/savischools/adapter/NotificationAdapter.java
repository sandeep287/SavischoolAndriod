package com.savitroday.savischools.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.databinding.MessageCellBinding;

import java.util.List;

/**
 * Created by harshitaahuja on 9/1/2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    
    Context context;
    List<Message> list;
    LayoutInflater layoutInflater;
    
    public class MyViewHolder extends RecyclerView.ViewHolder {
        
        MessageCellBinding mBinding;
        
        public MyViewHolder(MessageCellBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }
    
    public NotificationAdapter(Activity context, List<Message> list) {
        this.list = list;
        this.context = context;
        this.layoutInflater = (LayoutInflater)
                                      context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }
    
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MessageCellBinding binding = MessageCellBinding.inflate(layoutInflater,
                parent, false);
        return new MyViewHolder(binding);
    }
    
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mBinding.setMessage(list.get(position));
        holder.mBinding.setHandler(new Handler());
    }
    
    @Override
    public int getItemCount() {
        return list.size();
    }
    
    public class Handler {
        public void openNotificationDetail(Message notification) {
            
        }
    }
}