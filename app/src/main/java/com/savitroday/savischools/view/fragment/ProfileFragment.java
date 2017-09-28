package com.savitroday.savischools.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.R;

import com.savitroday.savischools.databinding.FragmentDashboardBinding;
import com.savitroday.savischools.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding mBindings;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBindings = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
      //  ((TabbedActivity)getActivity()).setActionBarTitle( "Profile");
        return mBindings.getRoot();
    }



}
