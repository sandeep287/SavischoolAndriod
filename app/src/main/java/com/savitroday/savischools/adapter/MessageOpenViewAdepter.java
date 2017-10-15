package com.savitroday.savischools.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.databinding.RecevemessageCellBinding;
import com.savitroday.savischools.databinding.SendmessageCellBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by owner on 10/11/2017.
 */

public class MessageOpenViewAdepter extends RecyclerView.Adapter<MessageOpenViewAdepter.viewHolder>

{
    Activity activity;
    List<Message> ldt;
    View view;
//    ViewDataBinding mBindings;
    AppCompatActivity apc;
   static int tempposition=0;
    SendmessageCellBinding sendmessageCellBinding;
    RecevemessageCellBinding recevemessageCellBinding;
    Message message;
    ViewGroup parent;
    
    public MessageOpenViewAdepter(Activity activity, List<Message> ldt,Message message) {
        this.activity = activity;
        this.ldt = ldt;
        this.message=message;
    }
    
    
    @Override
    public MessageOpenViewAdepter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        mBindings = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.sendmessage_cell,
//                parent, false);
        this.parent=parent;
        if (message.senderName.equals(ldt.get(tempposition).senderName)) {
            recevemessageCellBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recevemessage_cell,
               parent, false);
            return new MessageOpenViewAdepter.viewHolder(recevemessageCellBinding.getRoot());
        }
        else
        {
            sendmessageCellBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.sendmessage_cell,
                    parent, false);
            return new MessageOpenViewAdepter.viewHolder(sendmessageCellBinding.getRoot());
        }

    }
    
    @Override
    public void onBindViewHolder(MessageOpenViewAdepter.viewHolder holder, int position)
    {
       tempposition=tempposition+1;

        if (message.senderName.equals(ldt.get(position).senderName)) {

             recevemessageCellBinding.setMessage(ldt.get(position));
            if(ldt.get(position) != null) {
                recevemessageCellBinding.senderimage.setPadding(0,0,0,0);
                Picasso.with(activity).load((ldt.get(position)).iconMediaPath).into(recevemessageCellBinding.senderimage);
            }
        }
        else
        {
        sendmessageCellBinding.setMessage(ldt.get(position));
        }

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


