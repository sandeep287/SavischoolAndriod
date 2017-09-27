package com.savitroday.savischools.view.fragment;


import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.InvoiceAdapter;
import com.savitroday.savischools.adapter.MessageAdapter;
import com.savitroday.savischools.adapter.PopupRecyclerviewAdepter;
import com.savitroday.savischools.api.ApiException;
import com.savitroday.savischools.api.CustomCallAdapter;
import com.savitroday.savischools.api.UserRestService;
import com.savitroday.savischools.api.response.Dashboard;
import com.savitroday.savischools.databinding.FragmentDashboardBinding;
import com.savitroday.savischools.util.Constants;

import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {
    
    FragmentDashboardBinding mBindings;
    @Inject
    UserRestService userRestService;
    PopupWindow studentListPopup;
    Dashboard dashboard;
    
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
        
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.cell_header, mBindings.notificationListview, false);
        mBindings.notificationListview.addHeaderView(header, null, false);
        mBindings.invoiceListview.addHeaderView(header, null, false);
        getDashboardData();
        return mBindings.getRoot();
    }
    
    void getDashboardData() {
        mBindings.progressBar.setVisibility(View.VISIBLE);
        String parentId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_PARENT_ID);
        String schoolId = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_SCHOOL_ID);
        HashMap<String, String> map = new HashMap<>();
        map.put("schoolid", schoolId);
        map.put("parentId", parentId);
        userRestService.getDashboard(schoolId,parentId).enqueue(new CustomCallAdapter.CustomCallback<Dashboard>() {
            @Override
            public void success(Response<Dashboard> response) {
                dashboard = response.body();
                mBindings.setDashboard(dashboard);
                mBindings.setHandler(new Handler());
                InvoiceAdapter adapter = new InvoiceAdapter(getActivity(), dashboard.listStudentInvoiceModel);
                MessageAdapter messageAdapter = new MessageAdapter(getActivity(), dashboard.listSchoolMessagesModel);
                mBindings.notificationListview.setAdapter(messageAdapter);
                mBindings.invoiceListview.setAdapter(adapter);
                mBindings.progressBar.setVisibility(View.GONE);
                
            }
            
            @Override
            public void failure(ApiException e) {
                mBindings.progressBar.setVisibility(View.GONE);
            }
        });
    }
    
    public class Handler {
        public void onSelectChildren() {
            //TODO: show popup here
            showPopup();
        }
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (studentListPopup != null && studentListPopup.isShowing()) {
            studentListPopup.dismiss();
        }
        
    }
    
    void showPopup() {
        if (studentListPopup == null) {
            studentListPopup = new PopupWindow(this.getContext());
            View layout = getActivity().getLayoutInflater().inflate(R.layout.selectchildernpopup, null);
            studentListPopup.setContentView(layout);
            // Set content width and height
            studentListPopup.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
            studentListPopup.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
            
            RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.stlist);
            // final ImageView close = (ImageView) layout.findViewById(R.id.rejectReasonClose);
            LinearLayoutManager llm2 = new LinearLayoutManager(getContext());
            llm2.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm2);
            PopupRecyclerviewAdepter popupRecyclerviewAdepter = new PopupRecyclerviewAdepter(getActivity(),
                                                                                                    dashboard
                                                                                                            .listStudentModel);
            recyclerView.setAdapter(popupRecyclerviewAdepter);

//            close.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    studentListPopup.dismiss();
//                }
//            });
            // Closes the popup window when touch outside of it - when looses focus
            studentListPopup.setOutsideTouchable(false);
            studentListPopup.setFocusable(true);
            // Show anchored to button
            studentListPopup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //popup.showAsDropDown();
            studentListPopup.showAtLocation(mBindings.getRoot(), Gravity.CENTER, 20, 40);
            studentListPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    studentListPopup = null;
                }
            });
            
        }
    }
    
}
