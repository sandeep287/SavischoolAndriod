package com.savitroday.savischools.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.R;
import com.savitroday.savischools.databinding.FragmentCategoryBinding;
import com.savitroday.savischools.view.fragment.homework.HomeWorkFragment;
import com.savitroday.savischools.view.fragment.invoice.InvoicePaymentTabFragment;
import com.savitroday.savischools.view.fragment.messageNotification.NotificationMessageTabFragment;


public class CategoryFragment extends Fragment{
    
    FragmentCategoryBinding mBinding;
    public CategoryFragment() {
        // Required empty public constructor
    }
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_category, container, false);
        mBinding.setHandler(new Handler());
        return mBinding.getRoot();
    }
    
    public class Handler{
        public void onActivity(){
//            Fragment fragment = new InvoicePaymentTabFragment();
//            FragmentManager manager = getActivity().getSupportFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.add(R.id.flFragments, fragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
        }
    
        public void onAttendance(){
//            Fragment fragment = new AttendanceFragment();
//            FragmentManager manager = getActivity().getSupportFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.add(R.id.flFragments, fragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
        }
    
        public void onInvoicePayment(){
//            Fragment fragment = new InvoicePaymentTabFragment();
//           FragmentManager manager = getActivity().getSupportFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction();
//           transaction.add(R.id.flFragments, fragment);
//           transaction.addToBackStack(null);
//            transaction.commit();
        }
    
        public void onHomework(){
//            Fragment fragment = new HomeWorkFragment();
//            FragmentManager manager = getActivity().getSupportFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.add(R.id.flFragments, fragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
        }

        public void onMessageNotification(){
            Fragment fragment = new NotificationMessageTabFragment();
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.flFragments, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        public void  onChildernProfile(){
            Fragment fragment = new ParentProfileFragment();
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.flFragments, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        
        public void onBackPressed(){
            getActivity().onBackPressed();
        }
    }
   
}
