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
import com.savitroday.savischools.databinding.InvoiceCellPaymentBinding;
import com.savitroday.savischools.databinding.PendingPaymentListCellBinding;
import com.savitroday.savischools.helper.AutoResizeTextView;

import java.util.List;

/**
 * Created by owner on 10/5/2017.
 */

public class MakePaymentListAdepter extends RecyclerView.Adapter<MakePaymentListAdepter.viewHolder> {

    Activity activity;
    List<Invoice> ldt;
    PendingPaymentListCellBinding mBinding;
    AppCompatActivity apc;

    public MakePaymentListAdepter(Activity activity, List<Invoice> ldt) {
        this.activity = activity;
        this.ldt = ldt;
    }

    @Override
    public MakePaymentListAdepter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invoice_cell, parent, false);

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.pending_payment_list_cell,
                parent, false);

        return new MakePaymentListAdepter.viewHolder(mBinding.getRoot());

    }

    @Override
    public void onBindViewHolder(MakePaymentListAdepter.viewHolder holder, int position) {
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

