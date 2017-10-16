package com.savitroday.savischools.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.MakePaymentListAdepter;
import com.savitroday.savischools.api.response.Invoice;
import com.savitroday.savischools.databinding.FragmentMakePaymentBinding;
import com.savitroday.savischools.manager.InvoiceManager;
import com.savitroday.savischools.util.AlertUtil;
import com.savitroday.savischools.util.EventManager;
import com.savitroday.savischools.view.activity.MainActivity;

import java.util.List;

import javax.inject.Inject;


public class MakePaymentFragment extends Fragment implements EventManager.EventManagerDelegate {
RecyclerView paymentlist;
    @Inject
    InvoiceManager invoiceManager;
    List<Invoice> invoiceList;
    float totalAmount;
    View view;
    FragmentMakePaymentBinding mBinding;
    MakePaymentListAdepter makePaymentListAdepter;
    RelativeLayout progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_make_payment, container, false);
        mBinding.setHandler(new Handler());
       //view = inflater.inflate(R.layout.fragment_make_payment, container, false);
        paymentlist=(RecyclerView)view.findViewById(R.id.paymentlist);
        LinearLayoutManager llm2 = new LinearLayoutManager((MainActivity)getActivity());
        llm2.setOrientation(LinearLayoutManager.VERTICAL);
        paymentlist.setLayoutManager(llm2);
        MakePaymentListAdepter makePaymentListAdepter=new MakePaymentListAdepter(getActivity(),invoiceList);
        paymentlist.setAdapter(makePaymentListAdepter);
        return mBinding.getRoot();
    }
    public void getInvoiceData() {
        progressBar.setVisibility(View.VISIBLE);


        invoiceManager.getInvoicesTask().continueWith((task -> {
            progressBar.setVisibility(View.INVISIBLE);

            if (task.getResult() != null) {

                invoiceList = (List<Invoice>) task.getResult();
                //invoiceManager.getPendingInvoices();
                setAmount();
                makePaymentListAdepter.notifyDataSetChanged();
            } else {
                Exception e = task.getError();
                AlertUtil.showSnackbarWithMessage(e.getMessage(), view);
            }
            return null;
        }));

    }

    void setAmount() {
        totalAmount = invoiceManager.getTotalAmount();
        ((TextView)view.findViewById(R.id.totalamount)).setText("$" + (int) totalAmount);
    }

    @Override
    public void didReceivedEvent(int id, Object... args) {
        getInvoiceData();
    }
    public class Handler
    {
        public void onBackPressed(){
            getActivity().onBackPressed();
        }
    }
}
