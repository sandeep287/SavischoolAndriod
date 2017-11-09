package com.savitroday.savischools.view.fragment.invoice;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.MakePaymentListAdepter;
import com.savitroday.savischools.api.response.Invoices;
import com.savitroday.savischools.databinding.FragmentMakePaymentBinding;
import com.savitroday.savischools.manager.InvoiceManager;
import com.savitroday.savischools.util.AlertUtil;
import com.savitroday.savischools.util.EventManager;
import com.savitroday.savischools.view.activity.MainActivity;

import javax.inject.Inject;


public class MakePaymentFragment extends Fragment implements EventManager.EventManagerDelegate {
    
    @Inject
    InvoiceManager invoiceManager;
    
    Invoices invoices;

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
        MyApplication.getApp().getComponent().inject(this);
        LinearLayoutManager llm2 = new LinearLayoutManager((MainActivity) getActivity());
        llm2.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.paymentlist.setLayoutManager(llm2);
      getInvoiceData();
        return mBinding.getRoot();
    }
    
    public void getInvoiceData() {


       mBinding.progressBar.setVisibility(View.VISIBLE);
        invoiceManager.getInvoicesTask().continueWith((task -> {
           mBinding.progressBar.setVisibility(View.INVISIBLE);
            
            if (task.getResult() != null) {
                
              invoices = (Invoices)task.getResult();
                //invoiceManager.getPendingInvoices();
                MakePaymentListAdepter makePaymentListAdepter = new MakePaymentListAdepter(getActivity(), invoiceManager.getPendingInvoices());
                mBinding.paymentlist.setAdapter(makePaymentListAdepter);
                setAmount();
                makePaymentListAdepter.notifyDataSetChanged();
            } else {
                Exception e = task.getError();
                AlertUtil.showSnackbarWithMessage(e.getMessage(), mBinding.getRoot());
            }
            return null;
        }));
        
    }
    
    void setAmount() {
        Log.e("ammamama","adhcdc jk");
        mBinding.totalamount.setText("$" + (int) invoices.totalAmount);
    }
    
    @Override
    public void didReceivedEvent(int id, Object... args) {
        getInvoiceData();
    }
    
    public class Handler {
        public void onBackPressed() {
            getActivity().onBackPressed();
        }
    }
}
