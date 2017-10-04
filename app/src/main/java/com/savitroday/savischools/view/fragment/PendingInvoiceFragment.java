package com.savitroday.savischools.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.InvoiceListAdepter;
import com.savitroday.savischools.api.response.Invoice;
import com.savitroday.savischools.manager.InvoiceManager;
import com.savitroday.savischools.util.AlertUtil;
import com.savitroday.savischools.util.EventManager;

import java.util.List;

import javax.inject.Inject;

public class PendingInvoiceFragment extends Fragment implements EventManager.EventManagerDelegate
{
    RecyclerView pendingInvoiceList;
    @Inject
    InvoiceManager invoiceManager;
    List<Invoice> invoiceList;
    View view;
    float totalAmount;
    RelativeLayout progressBar;
    InvoiceListAdepter invoiceListAdepter;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        view = inflater.inflate(R.layout.fragment_pending_invoice, container, false);
        progressBar = (RelativeLayout) view.findViewById(R.id.progressBar);
        MyApplication.getApp().getComponent().inject(this);
        pendingInvoiceList = (RecyclerView) view.findViewById(R.id.pandinginvoiclist);
        
        LinearLayoutManager llm2 = new LinearLayoutManager(getActivity());
        llm2.setOrientation(LinearLayoutManager.VERTICAL);
        pendingInvoiceList.setLayoutManager(llm2);
        
        getInvoiceData();
        
        invoiceListAdepter = new InvoiceListAdepter(getActivity(), invoiceList);
        pendingInvoiceList.setAdapter(invoiceListAdepter);
        return view;
    }
    
    public void getInvoiceData() {
        progressBar.setVisibility(View.VISIBLE);
        
        Log.e("chack", "1");
        invoiceManager.getInvoicesTask().continueWith((task -> {
            progressBar.setVisibility(View.INVISIBLE);
            Log.e("chack", "2");
            if (task.getResult() != null) {
                Log.e("chack", "3");
                invoiceList = invoiceManager.getPendingInvoices();
                setAmount();
                invoiceListAdepter.notifyDataSetChanged();
            } else {
                Exception e = task.getError();
                AlertUtil.showSnackbarWithMessage(e.getMessage(), view);
            }
            return null;
        }));
        
    }
    
    
    void setAmount() {
        totalAmount = invoiceManager.getTotalAmount();
        InvoicePaymentFragment.totalamount.setText("$" + (int) totalAmount);
    }
    
    
    @Override
    public void didReceivedEvent(int id, Object... args) {
        getInvoiceData();
    }
}
