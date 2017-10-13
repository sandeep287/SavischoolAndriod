package com.savitroday.savischools.view.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.DashboardAdapter;
import com.savitroday.savischools.api.response.Dashboard;
import com.savitroday.savischools.api.response.Message;
import com.savitroday.savischools.databinding.DashboardHeaderBinding;
import com.savitroday.savischools.databinding.FragmentDashboardBinding;
import com.savitroday.savischools.manager.DashboardManager;
import com.savitroday.savischools.util.AlertUtil;
import com.savitroday.savischools.util.Event;
import com.savitroday.savischools.util.EventManager;
import com.savitroday.savischools.util.ListUtils;
import com.savitroday.savischools.view.activity.MainActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements EventManager.EventManagerDelegate {
    
    FragmentDashboardBinding mBindings;
    
    @Inject
    DashboardManager dashboardManager;
    Dashboard dashboard;
    DashboardAdapter dashboardAdapter;
    DashboardHeaderBinding headerBinding;
    
    public DashboardFragment() {
        // Required empty public constructor
    }
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBindings = DataBindingUtil.inflate(
                inflater, R.layout.fragment_dashboard, container, false);
        MyApplication.getApp().getComponent().inject(this);
        EventManager.getInstance().addObserver(this, Event.DASHBOARD_UPDATED);
        mBindings.setHandler(new Handler());
        
      
        headerBinding = DataBindingUtil.inflate(
                inflater, R.layout.dashboard_header, mBindings.listView, false);
        headerBinding.setHandler(new Handler());
        mBindings.listView.addHeaderView(headerBinding.getRoot());
        getDashboardData();
        return mBindings.getRoot();
    }
    
    void getDashboardData() {
        mBindings.progressBar.setVisibility(View.VISIBLE);
        
        dashboardManager.getDashboardTask().continueWith((task -> {
            mBindings.progressBar.setVisibility(View.GONE);
            if (task.getResult() != null) {
                dashboard = (Dashboard) task.getResult();
                mBindings.setDashboard(dashboard);
                headerBinding.setDashboard(dashboard);
                dashboardAdapter = new DashboardAdapter(getContext(), dashboard.listStudentInvoiceModel,
                                                               dashboard.listSchoolMessagesModel, dashboard
                                                                                                          .onlinePaymentIsAllow);
                mBindings.listView.setAdapter(dashboardAdapter);

                mBindings.listView.expandGroup(0);
                mBindings.listView.expandGroup(1);
                
                Picasso.with(getContext())
                        .load(dashboard.getDefaultStudent().iconMediaPath)
                        .placeholder(R.drawable.profile_img)
                        .into(headerBinding.studentImageview);
                setNavDrawer();
                setListClicks();
                setBadgeCount();
                
                
            } else {
                Exception e = task.getError();
                AlertUtil.showSnackbarWithMessage(e.getMessage(), mBindings.getRoot());
            }
            
            return null;
        }));
    }
    
    void setBadgeCount() {
        int count = dashboard.unreadMessagesNotification;
        if (count > 0) {
            headerBinding.notificationBadge.setText("" + count);
            headerBinding.notificationBadge.setVisibility(View.VISIBLE);
        } else {
            headerBinding.notificationBadge.setVisibility(View.GONE);
        }
    }
    
    void setListClicks() {
        mBindings.listView.setOnChildClickListener(new ExpandableListView
                                                               .OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if (groupPosition == 0) {
                    Message message = (Message) dashboardAdapter.getChild(groupPosition, childPosition);
                    
//                    FragmentManager manager = getActivity().getSupportFragmentManager();
//                    FragmentTransaction transaction = manager.beginTransaction();
//                    transaction.add(R.id.flFragments, NotificationMessageTabFragment.getInstance(message));
//                    transaction.addToBackStack(null);
//                    transaction.commit();
                } else {
                    //todo : push Invoice fragmnt
//                    Invoice invoice = (Invoice) dashboardAdapter.getChild(groupPosition,childPosition);
//
//                    FragmentManager manager = getActivity().getSupportFragmentManager();
//                    FragmentTransaction transaction = manager.beginTransaction();
//                    transaction.add(R.id.flFragments, new PendingInvoiceFragment());
//                    transaction.addToBackStack(null);
//                    transaction.commit();
                }
                
                return false;
            }
        });
        
        mBindings.listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                if (i == 0) {
//                    FragmentManager manager = getActivity().getSupportFragmentManager();
//                    FragmentTransaction transaction = manager.beginTransaction();
//                    transaction.add(R.id.flFragments, new NotificationMessageTabFragment());
//                    transaction.addToBackStack(null);
//                    transaction.commit();
                } else {
                    //todo : push Invoice fragmnt
//                    FragmentManager manager = getActivity().getSupportFragmentManager();
//                    FragmentTransaction transaction = manager.beginTransaction();
//                    transaction.add(R.id.flFragments, new PendingInvoiceFragment());
//                    transaction.addToBackStack(null);
//                    transaction.commit();
                }
                return true;
            }
        });
    }
    
    void setNavDrawer() {
        ((MainActivity) getActivity()).setNavigationList(dashboard.listStudentModel);
        ((MainActivity) getActivity()).setProfileButtonText(dashboard.parentName);
    }
    
    @Override
    public void didReceivedEvent(int id, Object... args) {
        getDashboardData();
    }
    
    public class Handler {
        public void onProfileTap() {
            if (dashboard != null) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.flFragments, ProfileFragment.getInstance(dashboard.getDefaultStudent()));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
        
        public void onNotificationTap() {
//            Fragment fragment = new NotificationMessageTabFragment();
//            FragmentManager manager = getActivity().getSupportFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.add(R.id.flFragments, fragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
        }
        
        public void onBackPressed() {
            getActivity().onBackPressed();
        }
        
    }
    
    
}
