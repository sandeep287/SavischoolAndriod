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
import com.savitroday.savischools.api.response.Invoice;
import com.savitroday.savischools.manager.InvoiceManager;
import com.savitroday.savischools.util.AlertUtil;
import com.savitroday.savischools.util.Event;
import com.savitroday.savischools.util.EventManager;
import com.savitroday.savischools.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class HistoryInvoiceFragment extends Fragment implements EventManager.EventManagerDelegate {
    RecyclerView historyinvoiclist;
    
    List<Invoice> invoiceList = new ArrayList<>();
   
    View view;
    RelativeLayout progressBar;
    @Inject
    InvoiceManager invoiceManager;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_history_invoice, container, false);
        
        MyApplication.getApp().getComponent().inject(this);
        
        progressBar = (RelativeLayout) view.findViewById(R.id.progressBar);
        historyinvoiclist = (RecyclerView) view.findViewById(R.id.historyinvoiclis);
        EventManager.getInstance().addObserver(this, Event.INVOICES_UPDATED);
        
        
        LinearLayoutManager llm2 = new LinearLayoutManager(getActivity());
        llm2.setOrientation(LinearLayoutManager.VERTICAL);
        historyinvoiclist.setLayoutManager(llm2);
        getInvoiceData();
        return view;
        
    }
    
    public void getInvoiceData() {
        progressBar.setVisibility(View.VISIBLE);
        
        invoiceManager.getInvoicesTask().continueWith((task -> {
            progressBar.setVisibility(View.INVISIBLE);
            if (task.getResult() != null) {
                InvoiceListAdapter invoiceListAdapter = new InvoiceListAdapter(getActivity(), invoiceList);
                historyinvoiclist.setAdapter(invoiceListAdapter);
                invoiceList = invoiceManager.getHistoyrInvoices();
                invoiceListAdapter.notifyDataSetChanged();
            } else {
                Exception e = task.getError();
                AlertUtil.showSnackbarWithMessage(e.getMessage(), view);
            }
            return null;
        }));
        
    }
    
    
    @Override
    public void didReceivedEvent(int id, Object... args) {
        getInvoiceData();
    }
}
