package com.savitroday.savischools.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.api.response.MessageNotification;
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
    int tempposition=0;
    SendmessageCellBinding sendmessageCellBinding;
    RecevemessageCellBinding recevemessageCellBinding;
    MessageNotification messageNotification;
    ViewGroup parent;
    
    public MessageOpenViewAdepter(Activity activity, List<Message> ldt, MessageNotification
                                                                                            messageNotification) {
        this.activity = activity;
        this.ldt = ldt;
        this.messageNotification = messageNotification;
    }
    
    
    @Override
    public MessageOpenViewAdepter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        this.parent=parent;
        if (tempposition==0) {
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



        if (position==0)
        {

             recevemessageCellBinding.setMessage(messageNotification);

            if(messageNotification.iconMediaPath != null) {
                recevemessageCellBinding.senderimage.setPadding(0,0,0,0);
                Picasso.with(activity).load(messageNotification.iconMediaPath).into(recevemessageCellBinding.senderimage);
            }
            if (messageNotification.messageAttachment!=null)
            {
                Picasso.with(activity).load(messageNotification.messageAttachment).into(recevemessageCellBinding.attachment);
            }
        }
        else
        {
                sendmessageCellBinding.setMessage(ldt.get(position-1));
        }

    }


    @Override
    public int getItemCount() {
        return ldt.size()+1;
    }
    
    class viewHolder extends RecyclerView.ViewHolder {
        
        public viewHolder(View itemView) {
            
            super(itemView);
        }
    }
}


