package com.savitroday.savischools.adapter;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.databinding.NotificationmessaegeCellBinding;
import com.savitroday.savischools.view.fragment.MessageOpenViewFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by owner on 10/6/2017.
 */

public class MessageTabAdepter extends RecyclerView.Adapter<MessageTabAdepter.viewHolder> {

    FragmentActivity activity;
    List<Message> ldt;
    NotificationmessaegeCellBinding mBinding;
    AppCompatActivity apc;

    public MessageTabAdepter(FragmentActivity activity, List<Message> ldt) {
        this.activity = activity;
        this.ldt = ldt;
    }

    @Override
    public MessageTabAdepter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invoice_cell, parent, false);

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.notificationmessaege_cell,
                parent, false);

        return new MessageTabAdepter.viewHolder(mBinding.getRoot());

    }

    @Override
    public void onBindViewHolder(MessageTabAdepter.viewHolder holder, int position) {
        mBinding.setMessage(ldt.get(position));
        if ((ldt.get(position)).iconMediaPath !=null) {
            Picasso.with(activity).load((ldt.get(position)).iconMediaPath).into(mBinding.imageView);
            mBinding.imageView.setPadding(0,0,0,0);
        }
        mBinding.messagecell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = MessageOpenViewFragment.getInstance(ldt.get(position));
                FragmentManager manager=activity.getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.flFragments, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
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