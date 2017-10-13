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
import com.savitroday.savischools.api.response.Student;
import com.savitroday.savischools.databinding.FragmentProfileBinding;
import com.squareup.picasso.Picasso;

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
        Picasso.with(getContext())
                .load(student.iconMediaPath)
                .placeholder(R.drawable.profile_img)
                .into(mBindings.studentImageview);
        mBindings.setHandler(new Handler());
        return mBindings.getRoot();
    }
    
    public class Handler {
        public void onBackPressed() {
            getActivity().onBackPressed();
        }
        
        public void onNotificationTap() {
   //  Fragment fragment = new NotificationMessageTabFragment();
//            FragmentManager manager = getActivity().getSupportFragmentManager();
//           FragmentTransaction transaction = manager.beginTransaction();
//            transaction.add(R.id.flFragments, fragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
        }
        
    }
    
}
