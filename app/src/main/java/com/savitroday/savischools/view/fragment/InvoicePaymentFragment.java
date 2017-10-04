package com.savitroday.savischools.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.savitroday.savischools.R;


public class InvoicePaymentFragment extends Fragment {
    TabLayout tabLayout;
    static TextView totalamount;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        
        View inflatedView = inflater.inflate(R.layout.fragment_invoice_payment, container, false);
        
        tabLayout = (TabLayout) inflatedView.findViewById(R.id.invoicepaymenttabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Pending Invoice"));
        tabLayout.addTab(tabLayout.newTab().setText("History"));
        final ViewPager viewPager = (ViewPager) inflatedView.findViewById(R.id.viewpagerfrag);
        viewPager.setAdapter(new PagerAdapter(getFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                
                
                viewPager.setCurrentItem(tab.getPosition());
                
            }
            
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                
            }
            
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                
            }
        });
        totalamount = (TextView) inflatedView.findViewById(R.id.totalamount);
        
        return inflatedView;
    }
    
    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;
        
        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }
        
        @Override
        public Fragment getItem(int position) {
            
            switch (position) {
                case 0:
                    PendingInvoiceFragment tab1 = new PendingInvoiceFragment();
                    
                    return tab1;
                case 1:
                    
                   // HistoryInvoiceFragment tab2 = new HistoryInvoiceFragment();
                   // return tab2;
                default:
                    return null;
            }
        }
        
        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
    
}