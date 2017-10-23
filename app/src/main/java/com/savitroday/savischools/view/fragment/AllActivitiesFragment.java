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
import com.savitroday.savischools.databinding.FragmentAllActivitiesBinding;
import com.savitroday.savischools.view.fragment.messageNotification.NotificationMessageTabFragment;

public class AllActivitiesFragment extends Fragment {
FragmentAllActivitiesBinding mBindings;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        mBindings = DataBindingUtil.inflate(inflater, R.layout.fragment_all_activities, container, false);

        return mBindings.getRoot();
    }

    public class Handler {
        public void onBackPressed() {
            getActivity().onBackPressed();
        }

        public void onNotificationTap() {
            Fragment fragment = new NotificationMessageTabFragment();
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.flFragments, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
