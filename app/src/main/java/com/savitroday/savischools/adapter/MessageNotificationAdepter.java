package com.savitroday.savischools.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.Invoice;
import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.databinding.InvoiceCellPaymentBinding;
import com.savitroday.savischools.databinding.MessageCellBinding;

import java.util.List;

/**
 * Created by owner on 10/6/2017.
 */

public class MessageNotificationAdepter extends RecyclerView.Adapter<MessageNotificationAdepter.viewHolder> {

    Activity activity;
    List<Message> ldt;
    MessageCellBinding mBinding;
    AppCompatActivity apc;

    public MessageNotificationAdepter(Activity activity, List<Message> ldt) {
        this.activity = activity;
        this.ldt = ldt;
    }

    @Override
    public MessageNotificationAdepter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invoice_cell, parent, false);

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.notificationmessaege_cell,
                parent, false);

        return new MessageNotificationAdepter.viewHolder(mBinding.getRoot());

    }

    @Override
    public void onBindViewHolder(MessageNotificationAdepter.viewHolder holder, int position) {
        mBinding.setMessage(ldt.get(position));

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
