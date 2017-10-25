package com.savitroday.savischools.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
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

public class MessageOpenViewAdapter extends RecyclerView.Adapter<MessageOpenViewAdapter.ViewHolder>

{
    Activity activity;
    List<Message> ldt;
    View view;
    //    ViewDataBinding mBindings;
    AppCompatActivity apc;
    int tempposition = 0;
    SendmessageCellBinding sendmessageCellBinding;
    RecevemessageCellBinding recevemessageCellBinding;
    MessageNotification messageNotification;
    ViewGroup parent;

    public MessageOpenViewAdapter(Activity activity, List<Message> ldt, MessageNotification
            messageNotification) {
        this.activity = activity;
        this.ldt = ldt;
        this.messageNotification = messageNotification;
    }


    @Override
    public MessageOpenViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        this.parent = parent;
        if (tempposition == 0) {
            recevemessageCellBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recevemessage_cell,
                    parent, false);
            return new MessageOpenViewAdapter.ViewHolder(recevemessageCellBinding);
        } else {
            sendmessageCellBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.sendmessage_cell,
                    parent, false);
            return new MessageOpenViewAdapter.ViewHolder(sendmessageCellBinding);
        }

    }

    @Override
    public void onBindViewHolder(MessageOpenViewAdapter.ViewHolder holder, int position) {
        tempposition = 0;
        tempposition = position + 1;


        if (position == 0) {

            RecevemessageCellBinding mBinding = (RecevemessageCellBinding) holder.bindingsuper;
            mBinding.setMessage(messageNotification);

            if (messageNotification.iconMediaPath != null) {
                mBinding.senderimage.setPadding(0, 0, 0, 0);
                Picasso.with(activity).load(messageNotification.iconMediaPath).into(mBinding.senderimage);
            }
            if (messageNotification.messageAttachment != null) {
                Picasso.with(activity).load(messageNotification.messageAttachment).into(mBinding.attachment);
            }
        } else {
            SendmessageCellBinding mBinding = (SendmessageCellBinding) holder.bindingsuper;
            mBinding.setMessage(ldt.get(position - 1));
        }

    }


    @Override
    public int getItemCount() {
        return ldt.size() + 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding bindingsuper;

        public ViewHolder(ViewDataBinding itemView) {
            super(itemView.getRoot());
            bindingsuper = itemView;
        }
    }

}


