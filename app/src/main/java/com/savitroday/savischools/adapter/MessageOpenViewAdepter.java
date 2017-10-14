package com.savitroday.savischools.adapter;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.api.response.Message;

import java.util.List;

/**
 * Created by owner on 10/11/2017.
 */

public class MessageOpenViewAdepter extends RecyclerView.Adapter<MessageOpenViewAdepter.viewHolder>

{
    Activity activity;
    List<Message> ldt;
    ViewDataBinding mBindings;
    AppCompatActivity apc;
    int tempposition;
    
    public MessageOpenViewAdepter(Activity activity, List<Message> ldt) {
        this.activity = activity;
        this.ldt = ldt;
    }
    
    
    @Override
    public MessageOpenViewAdepter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        
        
        return new MessageOpenViewAdepter.viewHolder(mBindings.getRoot());
    }
    
    @Override
    public void onBindViewHolder(MessageOpenViewAdepter.viewHolder holder, int position) {
        
        
    }
    
    
    @Override
    public int getItemCount() {
        return ldt.size();
    }
    
    class viewHolder extends RecyclerView.ViewHolder {
        
        public viewHolder(View itemView) {
            
            super(itemView);
        }
    }
}


