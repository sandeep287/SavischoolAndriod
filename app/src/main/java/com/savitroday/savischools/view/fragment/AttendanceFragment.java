package com.savitroday.savischools.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.R;
import com.savitroday.savischools.databinding.FragmentAttandenceBinding;

public class AttendanceFragment extends Fragment {

    FragmentAttandenceBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_attandence, container, false);
        mBinding.setHandler(new Handler());
        return mBinding.getRoot();
    }

    public class Handler {
        public void onBackPressed() {
            getActivity().onBackPressed();
        }
    }
}
