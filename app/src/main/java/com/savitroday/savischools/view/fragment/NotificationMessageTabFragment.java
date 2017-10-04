package com.savitroday.savischools.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.PagerAdapter;
import com.savitroday.savischools.api.response.Message;

public class NotificationMessageTabFragment extends Fragment implements View.OnClickListener {
    
    TabLayout tabLayout;
    Message message;
    boolean toDetail = false;
    
    public static NotificationMessageTabFragment getInstance(Message message) {
        NotificationMessageTabFragment fragment = new NotificationMessageTabFragment();
       
        fragment.toDetail = true;
        fragment.message = message;
        return fragment;
    }
    
    public NotificationMessageTabFragment() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflatedView = inflater.inflate(R.layout.fragment_notification_message_tab, container, false);
        
        if(toDetail){
            if (message.isNotification) {
                //todo : send to notification detail
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.flFragments, NotificationOpenViewFrag.getInstance(message));
                transaction.addToBackStack(null);
                transaction.commit();
            } else {
                //todo : send to message detail later
            }
        }
        
        
        tabLayout = (TabLayout) inflatedView.findViewById(R.id.tabLayout);
        tabLayout.setTabTextColors(ContextCompat.getColor(getContext(),R.color.white), ContextCompat.getColor(getContext(),R.color.white));
        tabLayout.addTab(tabLayout.newTab().setText("NOTIFICATION"));
        tabLayout.addTab(tabLayout.newTab().setText("MESSAGES"));
        ImageButton button = (ImageButton) inflatedView.findViewById(R.id.backButton);
        button.setOnClickListener(this);
        ImageView profile = (ImageView) inflatedView.findViewById(R.id.profileButton);
        profile.setOnClickListener(this);
        final ViewPager viewPager = (ViewPager) inflatedView.findViewById(R.id.viewpager);
        
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
        
        return inflatedView;
    }
    
    
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backButton) {
            getActivity().onBackPressed();
        } else if (view.getId() == R.id.profileButton) {
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            //TODO: send student object
            transaction.add(R.id.flFragments, ProfileFragment.getInstance(null));
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}