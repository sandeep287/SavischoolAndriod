package com.savitroday.savischools.view.fragment.invoice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.InvoiceListAdapter;
import com.savitroday.savischools.api.response.Invoices;
import com.savitroday.savischools.manager.InvoiceManager;
import com.savitroday.savischools.util.AlertUtil;
import com.savitroday.savischools.util.EventManager;

import javax.inject.Inject;

public class PendingInvoiceFragment extends Fragment implements EventManager.EventManagerDelegate {
    RecyclerView pendingInvoiceList;
    @Inject
    InvoiceManager invoiceManager;
    Invoices invoices;
    View view;
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
        
        
        getInvoiceData();
        return view;
    }
    
    public void getInvoiceData() {
        progressBar.setVisibility(View.VISIBLE);
        
        invoiceManager.getInvoicesTask().continueWith((task -> {
            progressBar.setVisibility(View.INVISIBLE);
            
            if (task.getResult() != null) {
                invoices = (Invoices) task.getResult();
                invoiceListAdapter = new InvoiceListAdapter(getActivity(), invoiceManager.getPendingInvoices());
                pendingInvoiceList.setAdapter(invoiceListAdapter);
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
        InvoicePaymentTabFragment.totalamount.setText("$" + (int) invoices.totalAmount);
    }
    
    
    @Override
    public void didReceivedEvent(int id, Object... args) {
        getInvoiceData();
    }
}
