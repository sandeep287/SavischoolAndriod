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
import com.savitroday.savischools.databinding.InvoiceCellBinding;

import java.util.List;

/**
 * Created by owner on 10/3/2017.
 */

public class InvoiceListAdepter extends RecyclerView.Adapter<InvoiceListAdepter.viewHolder> {

    Activity activity;
    List<Invoice> ldt;
    InvoiceCellBinding mBinding;
    AppCompatActivity apc;

    public InvoiceListAdepter(Activity activity, List<Invoice> ldt) {
        this.activity = activity;
        this.ldt = ldt;
    }

    @Override
    public InvoiceListAdepter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invoice_cell, parent, false);

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.invoice_cell, parent, false);

        return new viewHolder(mBinding.getRoot());

    }

    @Override
    public void onBindViewHolder(InvoiceListAdepter.viewHolder holder, int position)
    {
      mBinding.setInvoice(ldt.get(position));
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
