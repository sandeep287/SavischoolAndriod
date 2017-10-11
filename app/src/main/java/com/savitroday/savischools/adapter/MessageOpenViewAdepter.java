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
import com.savitroday.savischools.util.Constants;

import java.util.List;

/**
 * Created by owner on 10/11/2017.
 */

public class MessageOpenViewAdepter  extends RecyclerView.Adapter<MessageOpenViewAdepter.viewHolder>

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

        if (Constants.SHARED_PREFERENCES_USER_ID.equals(((ldt.get(tempposition)).userId).equals(Constants.SHARED_PREFERENCES_USER_ID)))
        {
            mBindings = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.sendmessage_cell, parent, false);
        }
        else
        {
            mBindings = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recevemessage_cell, parent, false);
        }

        return new MessageOpenViewAdepter.viewHolder(mBindings.getRoot());
    }

    @Override
    public void onBindViewHolder(MessageOpenViewAdepter.viewHolder holder, int position)
    {
        tempposition=position+1;
        if (Constants.SHARED_PREFERENCES_USER_ID.equals(((ldt.get(position)).userId).equals(Constants.SHARED_PREFERENCES_USER_ID)))
        {

            SendmessageCellBinding sendmessageCellBinding=(SendmessageCellBinding) mBindings;
            sendmessageCellBinding.setMessage(ldt.get(position));
        }
        else
        {
            RecevemessageCellBinding recevemessageCellBinding=(RecevemessageCellBinding)mBindings;
            recevemessageCellBinding.setMessage(ldt.get(position));
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


