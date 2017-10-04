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
import com.savitroday.savischools.adapter.DashboardAdapter;
import com.savitroday.savischools.adapter.InvoiceListAdepter;
import com.savitroday.savischools.api.response.Dashboard;
import com.savitroday.savischools.api.response.Invoice;
import com.savitroday.savischools.manager.DashboardManager;
import com.savitroday.savischools.util.AlertUtil;
import com.savitroday.savischools.util.Event;
import com.savitroday.savischools.util.EventManager;
import com.savitroday.savischools.view.activity.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PendingInvoiceFragment extends Fragment implements EventManager.EventManagerDelegate

{
    RecyclerView pandinginvoiclist;
    @Inject
    DashboardManager dashboardManager;
    List<Invoice> invoiceList;
    View view;
    float totalamount;
    Dashboard dashboard;
    RelativeLayout progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       view =inflater.inflate(R.layout.fragment_pending_invoice, container, false);
        progressBar=(RelativeLayout) view.findViewById(R.id.progressBar);
        MyApplication.getApp().getComponent().inject(this);
        pandinginvoiclist =(RecyclerView)view.findViewById(R.id.pandinginvoiclist);
        EventManager.getInstance().addObserver(this, Event.DASHBOARD_UPDATED);
        getInvoicelistdaata();
        LinearLayoutManager llm2 = new LinearLayoutManager((MainActivity)getActivity());
        llm2.setOrientation(LinearLayoutManager.VERTICAL);
        pandinginvoiclist.setLayoutManager(llm2);
        InvoiceListAdepter invoiceListAdepter=new InvoiceListAdepter((MainActivity)getActivity(),invoiceList);
        pandinginvoiclist.setAdapter(invoiceListAdepter);
        return view;
    }
    public void getInvoicelistdaata()
    {
        progressBar.setVisibility(View.VISIBLE);

        Log.e("chack","1");
        dashboardManager.getDashboardTask().continueWith((task -> {
            progressBar.setVisibility(View.INVISIBLE);
            Log.e("chack","2");
            if(task.getResult() != null){
                Log.e("chack","3");
                dashboard =(Dashboard) task.getResult();
               invoiceList= shortPanding(dashboard.listStudentInvoiceModel);


            }
            else
            {
                Exception e = task.getError();
                AlertUtil.showSnackbarWithMessage(e.getMessage(),view);
            }

            return null;
        }));

    }
                public List<Invoice> shortPanding(List<Invoice> list)
                 {
                     List<Invoice> pandinginvoices=new ArrayList<>();
                     for (int i=0;i<list.size();i++)
                     {
                         if(list.get(i).status.equals("Pending"))
                         {
                             totalamount=totalamount+list.get(i).amount;
                             Log.e("chack",""+totalamount);
                           pandinginvoices.add(list.get(i));

                         }
                     }
                     InvoicePaymentFragment.totalamount.setText("$"+(int)totalamount);
                     return pandinginvoices;
                 }


    @Override
    public void didReceivedEvent(int id, Object... args) {
        getInvoicelistdaata();
    }
}
