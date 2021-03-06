package com.savitroday.savischools.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.Student;
import com.savitroday.savischools.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    
    Student student;
    FragmentProfileBinding mBindings;
    
    public static ProfileFragment getInstance(Student student) {
        ProfileFragment profileFragment = new ProfileFragment();
        profileFragment.student = student;
        return profileFragment;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBindings = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        mBindings.setStudent(student);
        mBindings.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        return mBindings.getRoot();
    }
    
}
