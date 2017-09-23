package com.savitroday.savischools.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.savitroday.savischools.api.response.Invoice;
import com.savitroday.savischools.databinding.InvoiceCellBinding;

import java.util.List;

/**
 * Created by Harshita Ahuja on 22/09/17.
 */

public class InvoiceAdapter implements ListAdapter {
    
    private Context context;
    List<Invoice> invoiceList;
    
    public InvoiceAdapter(Context context, List<Invoice> invoiceList) {
        this.context = context;
        this.invoiceList = invoiceList;
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
        return invoiceList.size();
    }
    
    @Override
    public Object getItem(int i) {
        return invoiceList.get(i);
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
        final Invoice invoice = (Invoice) getItem(i);
        InvoiceCellBinding binding = DataBindingUtil.getBinding(view);
        if (binding == null) {
             binding = InvoiceCellBinding.inflate(LayoutInflater.from(viewGroup.getContext()),
                    viewGroup, false);
           
        }
        binding.setInvoice(invoice);
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
