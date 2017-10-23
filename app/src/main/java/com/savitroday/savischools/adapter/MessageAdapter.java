package com.savitroday.savischools.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.MessageNotification;
import com.savitroday.savischools.databinding.MessageListCellBinding;
import com.savitroday.savischools.view.fragment.messageNotification.MessageOpenViewFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Harshita Ahuja on 22/09/17.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {
    
    private Context context;
    List<MessageNotification> messageList;
    LayoutInflater layoutInflater;
    
    public MessageAdapter(Context context, List<MessageNotification> messageList) {
        this.context = context;
        this.messageList = messageList;
        this.layoutInflater = (LayoutInflater)
                                      context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }
    
    public class MyViewHolder extends RecyclerView.ViewHolder {
        
        MessageListCellBinding mBinding;
        
        public MyViewHolder(MessageListCellBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }
    
    
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MessageListCellBinding binding = MessageListCellBinding.inflate(layoutInflater,
                parent, false);
        return new MyViewHolder(binding);
    }
    
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mBinding.setMessage(messageList.get(position));
        holder.mBinding.setHandler(new Handler());
        if ((messageList.get(position)).iconMediaPath != null) {
            Picasso.with(context).load((messageList.get(position)).iconMediaPath).into(holder.mBinding.imageView);
            holder.mBinding.imageView.setPadding(0, 0, 0, 0);
        }
        if (messageList.get(position).studentName != null) {
            if ((!messageList.get(position).studentName.equals(""))) {
                holder.mBinding.studentName.setVisibility(View.VISIBLE);
            }
        }
        if ((messageList.get(position).messageAttachmentType!=null)&&(!messageList.get(position).messageAttachmentType.equals(".txt")))
        {
            if(messageList.get(position).messageAttachmentType.equals(".img"))
            {
                holder.mBinding.imageView.setImageResource(R.drawable.attached_file);
            }
            else if (messageList.get(position).messageAttachmentType.equals(".doc"))
            {

            }
        }
        
    }
    
    @Override
    public int getItemCount() {
        return messageList.size();
    }
    
    public class Handler {
        
        public void openMessageDetail(MessageNotification message) {
            Fragment fragment = MessageOpenViewFragment.getInstance(message);
            FragmentManager manager = ((FragmentActivity) context).getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.flFragments, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
    
}
