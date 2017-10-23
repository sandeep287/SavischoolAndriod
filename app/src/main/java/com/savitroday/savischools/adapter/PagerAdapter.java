package com.savitroday.savischools.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.savitroday.savischools.view.fragment.messageNotification.MessagesFragment;
import com.savitroday.savischools.view.fragment.messageNotification.NotificationFragment;

/**
 * Created by owner on 9/29/2017.
 */


public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    
    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    
    @Override
    public Fragment getItem(int position) {
        
        switch (position)
        {
            case 0:
                NotificationFragment tab1 = new NotificationFragment();
              return tab1;
            case 1:
                MessagesFragment tab2 = new MessagesFragment();

                return tab2;
            default:
                return null;
        }
    }
    
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}