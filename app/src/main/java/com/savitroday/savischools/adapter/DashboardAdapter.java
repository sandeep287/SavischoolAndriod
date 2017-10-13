package com.savitroday.savischools.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.Invoice;
import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.databinding.InvoiceCellBinding;
import com.savitroday.savischools.databinding.MessageCellBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Harshita Ahuja on 28/09/17.
 */

public class DashboardAdapter extends BaseExpandableListAdapter {
    private Context context;
    List<Invoice> invoiceList;
    List<Message> messageList;
    boolean onlinePaymentIsAllow;
    
    public DashboardAdapter(Context context, List<Invoice> invoiceList, List<Message> messageList, boolean onlinePaymentIsAllow) {
        this.context = context;
        this.invoiceList = invoiceList;
        this.messageList = messageList;
        this.onlinePaymentIsAllow = onlinePaymentIsAllow;
    }
    
    
    @Override
    public int getGroupCount() {
        return 2;
    }
    
    @Override
    public int getChildrenCount(int i) {
        if (i == 0)
            return messageList.size();
        return invoiceList.size();
    }
    
    @Override
    public Object getGroup(int i) {
        if (i == 0)
            return "Notification & Messages";
        return "Invoice";
    }
    
    @Override
    public Object getChild(int i, int i1) {
        if (i == 0)
            return messageList.get(i1);
        return invoiceList.get(i1);
    }
    
    @Override
    public long getGroupId(int i) {
        return i;
    }
    
    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }
    
    @Override
    public boolean hasStableIds() {
        return false;
    }
    
    @Override
    public View getGroupView(int i, boolean b, View convertView, ViewGroup viewGroup) {
        String headerTitle = (String) getGroup(i);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                                                                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.cell_header, null);
        }
        
        TextView lblListHeader = (TextView) convertView
                                                    .findViewById(R.id.title);
        lblListHeader.setText(headerTitle);
        
        return convertView;
    }
    
    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (i == 0) {
            final Message message = (Message) getChild(i, i1);
            
            MessageCellBinding binding = MessageCellBinding.inflate(LayoutInflater.from(viewGroup.getContext()),
                    viewGroup, false);
            
            binding.setMessage(message);
            if (message.iconMediaPath != null) {
                binding.imageView.setPadding(0, 0, 0, 0);
                Picasso.with(context).load(message.iconMediaPath).into(binding.imageView);
            } else {
                binding.imageView.setPadding(15, 15, 15, 15);
                if (message.isNotification) {
                    binding.imageView.setImageResource(R.drawable.notification);
                } else {
                    binding.imageView.setImageResource(R.drawable.message);
                }
            }
            if (!message.isNotification && message.messageAttachment != null && message.messageAttachment.length() >
                                                                                        0) {
                //todo: show camera icon
            }
            binding.executePendingBindings();
            
            return binding.getRoot();
        } else {
            final Invoice invoice = (Invoice) getChild(i, i1);
            
            InvoiceCellBinding binding = InvoiceCellBinding.inflate(LayoutInflater.from(viewGroup.getContext()),
                    viewGroup, false);
            binding.setInvoice(invoice);
            if (invoice.status.equalsIgnoreCase("Pending")) {
                binding.paynowButton.setVisibility(View.VISIBLE);
                if (onlinePaymentIsAllow) {
                    binding.paynowButton.setBackgroundResource(R.drawable.turqoise_rounded_background);
                    binding.paynowButton.setClickable(true);
                } else {
                    binding.paynowButton.setBackgroundResource(R.drawable.cool_grey_rounded_background);
                    binding.paynowButton.setClickable(false);
                }
            } else if (invoice.status.equalsIgnoreCase("Paid")) {
                binding.paynowButton.setVisibility(View.GONE);
            }
            binding.executePendingBindings();
            
            return binding.getRoot();
        }
    }
    
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
    
    
}
