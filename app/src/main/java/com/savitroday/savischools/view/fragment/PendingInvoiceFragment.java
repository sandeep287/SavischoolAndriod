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
import com.savitroday.savischools.adapter.InvoiceListAdapter;
import com.savitroday.savischools.api.response.Invoice;
import com.savitroday.savischools.manager.InvoiceManager;
import com.savitroday.savischools.util.AlertUtil;
import com.savitroday.savischools.util.EventManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PendingInvoiceFragment extends Fragment implements EventManager.EventManagerDelegate {
    RecyclerView pendingInvoiceList;
    @Inject
    InvoiceManager invoiceManager;
    List<Invoice> invoiceList = new ArrayList<>();
    View view;
    float totalAmount;
    RelativeLayout progressBar;
    InvoiceListAdapter invoiceListAdapter;
    
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
        
    
       // getInvoiceData();
        return view;
    }
    
    public void getInvoiceData() {
        progressBar.setVisibility(View.VISIBLE);
        
        invoiceManager.getInvoicesTask().continueWith((task -> {
            progressBar.setVisibility(View.INVISIBLE);
            if (task.getResult() != null) {
                invoiceListAdapter = new InvoiceListAdapter(getActivity(), invoiceList);
                pendingInvoiceList.setAdapter(invoiceListAdapter);
                invoiceList = invoiceManager.getPendingInvoices();
                setAmount();
                invoiceListAdapter.notifyDataSetChanged();
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
