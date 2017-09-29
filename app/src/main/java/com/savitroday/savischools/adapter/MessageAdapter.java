package com.savitroday.savischools.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.Invoice;
import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.databinding.InvoiceCellBinding;
import com.savitroday.savischools.databinding.MessageCellBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Harshita Ahuja on 22/09/17.
 */

public class MessageAdapter implements ListAdapter {
    
    private Context context;
    List<Message> messageList;
    
    public MessageAdapter(Context context, List<Message> invoiceList) {
        this.context = context;
        this.messageList = invoiceList;
    }
    
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
    
    @Override
    public boolean isEnabled(int i) {
        return false;
    }
    
    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        
    }
    
    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        
    }
    
    @Override
    public int getCount() {
        return messageList.size();
    }
    
    @Override
    public Object getItem(int i) {
        return messageList.get(i);
    }
    
    @Override
    public long getItemId(int i) {
        return i;
    }
    
    @Override
    public boolean hasStableIds() {
        return false;
    }
    
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Message message = (Message) getItem(i);
        MessageCellBinding binding = DataBindingUtil.getBinding(view);
        if (binding == null) {
             binding = MessageCellBinding.inflate(LayoutInflater.from(viewGroup.getContext()),
                    viewGroup, false);
        }
        binding.setMessage(message);
        if(message.iconMediaPath != null) {
            binding.imageView.setPadding(0,0,0,0);
            Picasso.with(context).load(message.iconMediaPath).into(binding.imageView);
        }
        else
        {
            binding.imageView.setPadding(10,10,10,10);
            binding.imageView.setImageResource(R.drawable.notification);
            binding.imageView.setBackgroundResource(R.drawable.gradient_circle);
        }
    
        binding.executePendingBindings();
    
        return binding.getRoot();
    }
    
    @Override
    public int getItemViewType(int i) {
        return 0;
    }
    
    @Override
    public int getViewTypeCount() {
        return 1;
    }
    
    @Override
    public boolean isEmpty() {
        return false;
    }
}
