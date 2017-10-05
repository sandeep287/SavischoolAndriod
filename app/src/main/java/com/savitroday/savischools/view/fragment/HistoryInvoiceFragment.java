package com.savitroday.savischools.view.fragment;

import android.content.Context;
import android.net.Uri;
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
import com.savitroday.savischools.api.response.Dashboard;
import com.savitroday.savischools.api.response.Invoice;
import com.savitroday.savischools.manager.DashboardManager;
import com.savitroday.savischools.manager.InvoiceManager;
import com.savitroday.savischools.util.AlertUtil;
import com.savitroday.savischools.util.Event;
import com.savitroday.savischools.util.EventManager;
import com.savitroday.savischools.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class HistoryInvoiceFragment extends Fragment  implements EventManager.EventManagerDelegate {
    RecyclerView  historyinvoiclist;
    List<Invoice> invoiceList;
    Dashboard dashboard;
    View view;
    RelativeLayout progressBar;
    @Inject
    InvoiceManager invoiceManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MyApplication.getApp().getComponent().inject(this);
        view =inflater.inflate(R.layout.fragment_history_invoice, container, false);
        progressBar=(RelativeLayout) view.findViewById(R.id.progressBar);
        historyinvoiclist =(RecyclerView)view.findViewById(R.id.historyinvoiclis);
        EventManager.getInstance().addObserver(this, Event.DASHBOARD_UPDATED);

        // dashboardManager =new DashboardManager();

        getInvoiceData();
        LinearLayoutManager llm2 = new LinearLayoutManager((MainActivity)getActivity());
        llm2.setOrientation(LinearLayoutManager.VERTICAL);
        historyinvoiclist.setLayoutManager(llm2);
        InvoiceListAdepter invoiceListAdepter=new InvoiceListAdepter(getActivity(),invoiceList);
        historyinvoiclist.setAdapter(invoiceListAdepter);
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
           //     invoiceList = invoiceManager.getHistoyrInvoices();
                //invoiceListAdepter.notifyDataSetChanged();
            } else {
                Log.e("chack", "333333333333333333");
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
