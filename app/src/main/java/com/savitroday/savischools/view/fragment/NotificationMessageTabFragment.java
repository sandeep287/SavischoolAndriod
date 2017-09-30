package com.savitroday.savischools.view.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.PagerAdapter;

public class NotificationMessageTabFragment extends Fragment {

    TabLayout tabLayout;
    public NotificationMessageTabFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflatedView=inflater.inflate(R.layout.fragment_notification_message_tab, container, false);
        tabLayout = (TabLayout) inflatedView.findViewById(R.id.tabLayout);
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"),Color.parseColor("#ffffff"));
        tabLayout.addTab(tabLayout.newTab().setText("NOTIFICATION"));
        tabLayout.addTab(tabLayout.newTab().setText("MESSAGES"));

        final ViewPager viewPager = (ViewPager) inflatedView.findViewById(R.id.viewpager);

        viewPager.setAdapter(new PagerAdapter(getFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

}