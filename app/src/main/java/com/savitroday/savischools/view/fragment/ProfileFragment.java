package com.savitroday.savischools.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.api.response.Dashboard;
import com.savitroday.savischools.api.response.Student;
import com.savitroday.savischools.databinding.FragmentProfileBinding;
import com.savitroday.savischools.manager.DashboardManager;
import com.savitroday.savischools.util.Event;
import com.savitroday.savischools.util.EventManager;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class ProfileFragment extends Fragment implements EventManager.EventManagerDelegate {
    
    Student student;
    FragmentProfileBinding mBindings;
    @Inject
    DashboardManager dashboardManager;
    
    public static ProfileFragment getInstance(Student student) {
        ProfileFragment profileFragment = new ProfileFragment();
        profileFragment.student = student;
        return profileFragment;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBindings = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        MyApplication.getApp().getComponent().inject(this);
        mBindings.setStudent(student);
        setImage();
        mBindings.setHandler(new Handler());
        EventManager.getInstance().addObserver(this, Event.DASHBOARD_UPDATED);
        getDashboardData(false);
        return mBindings.getRoot();
    }
    
    @Override
    public void didReceivedEvent(int id, Object... args) {
        if (id == Event.DASHBOARD_UPDATED) {
            getDashboardData(true);
        }
    }
    
    void getDashboardData(boolean refresh) {
        dashboardManager.getDashboardTask().continueWithTask((task -> {
            Dashboard dashboard = (Dashboard) task.getResult();
            if (refresh) {
                student = dashboard.getDefaultStudent();
                setImage();
                mBindings.setStudent(student);
            }
            setBadgeCount(dashboard.unreadMessagesNotification);
            return null;
        }));
    }
    
    void setImage() {
        if (student != null && student.iconMediaPath != null) {
            mBindings.studentImageview.setImageDrawable(null);
            Picasso.with(getContext())
                    .load(student.iconMediaPath)
                    .placeholder(R.drawable.profile_img)
                    .into(mBindings.studentImageview);
        }
        else
        {
            mBindings.studentImageview.setImageResource(R.drawable.profile_img);
        }
    }
    
    void setBadgeCount(int count) {
        if (count > 0) {
            mBindings.notificationBadge.setText("" + count);
            mBindings.notificationBadge.setVisibility(View.VISIBLE);
        } else {
            mBindings.notificationBadge.setVisibility(View.GONE);
        }
    }
    
    public class Handler {
        public void onBackPressed() {
            getActivity().onBackPressed();
        }
        
        public void onNotificationTap() {
//           Fragment fragment = new NotificationMessageTabFragment();
//            FragmentManager manager = getActivity().getSupportFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.add(R.id.flFragments, fragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
        }
    }
}
