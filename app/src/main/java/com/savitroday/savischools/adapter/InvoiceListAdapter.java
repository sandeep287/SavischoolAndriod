package com.savitroday.savischools.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.Invoice;
import com.savitroday.savischools.databinding.InvoiceCellPaymentBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owner on 10/3/2017.
 */

public class InvoiceListAdapter extends RecyclerView.Adapter<InvoiceListAdapter.viewHolder> {
    
    Activity activity;
    List<Invoice> invoiceList = new ArrayList<>();
    InvoiceCellPaymentBinding mBinding;
    
    public InvoiceListAdapter(Activity activity, List<Invoice> invoiceList) {
        this.activity = activity;
        this.invoiceList = invoiceList;
    }
    
    @Override
    public InvoiceListAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invoice_cell, parent, false);
        
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.invoice_cell_payment,
                parent, false);
        
        return new viewHolder(mBinding.getRoot());
        
    }
    
    @Override
    public void onBindViewHolder(InvoiceListAdapter.viewHolder holder, int position) {
        mBinding.setInvoice(invoiceList.get(position));
    }
    
    @Override
    public int getItemCount() {
        return invoiceList.size();
    }
    
    class viewHolder extends RecyclerView.ViewHolder {
        
        public viewHolder(View itemView) {
            
            super(itemView);
        }
    }
    
}
