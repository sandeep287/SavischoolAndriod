package com.savitroday.savischools.view.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.StudentListAdapter;
import com.savitroday.savischools.api.response.Student;
import com.savitroday.savischools.helper.OnItemClickListener;
import com.savitroday.savischools.manager.DashboardManager;
import com.savitroday.savischools.view.fragment.CategoryFragment;
import com.savitroday.savischools.view.fragment.DashboardFragment;
import com.savitroday.savischools.view.fragment.invoice.InvoicePaymentTabFragment;
import com.savitroday.savischools.view.fragment.ParentProfileFragment;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    
    @Inject
    DashboardManager dashboardManager;
    RelativeLayout mProgressDialog;
    NavigationView navigationView;
    RecyclerView recyclerView;
    StudentListAdapter studentListAdapter;
    DrawerLayout drawer;
    Button profileButton;
    LinearLayout bootomTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Used to inject
         MyApplication.getApp().getComponent().inject(this);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        
        ImageButton drawertoggle = (ImageButton) findViewById(R.id.studentButton);
        drawertoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        ((ImageButton)findViewById(R.id.categoryButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.flFragments, new CategoryFragment());
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);
        recyclerView = (RecyclerView) hView.findViewById(R.id.studentListRecyclerView);
        //TODO : uncomment this to stop drawer with swipe
        //drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        
        
        mProgressDialog = (RelativeLayout) findViewById(R.id.progressBar);
        bootomTab = (LinearLayout) findViewById(R.id.bottom_tab);
        Button logout = (Button) findViewById(R.id.logout_button);
        logout.setOnClickListener(this);
        profileButton = (Button) findViewById(R.id.profile_button);
        profileButton.setOnClickListener(this);
        ImageButton category = (ImageButton) findViewById(R.id.categoryButton);
        //category.setOnClickListener(this);
        ImageButton activities = (ImageButton) findViewById(R.id.activityButton);
       // activities.setOnClickListener(this);
        addDashboard();
    }
    
    public void setNavigationList(List<Student> studentList) {
        studentListAdapter = new StudentListAdapter(this, studentList, new OnItemClickListener() {
            @Override
            public void onItemClick(Student student) {
                dashboardManager.setDefaultStudent(student);
                drawer.closeDrawer(GravityCompat.START);
            }
        });
        recyclerView.setAdapter(studentListAdapter);
        
    }
    
    public void setProfileButtonText(String name){
        profileButton.setText(name);
    }
    
    
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() > 1) {
                super.onBackPressed();
            } else {
                finishAffinity();
            }
        }
    }
    
    void addDashboard() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.flFragments, new DashboardFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
    
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.logout_button) {
            MyApplication.getApp().logout(this);
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        } else if (view.getId() == R.id.profile_button) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.flFragments, new ParentProfileFragment());
            transaction.addToBackStack(null);
            transaction.commit();
            drawer.closeDrawer(GravityCompat.START);
        } else if (view.getId() == R.id.categoryButton) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.flFragments, new CategoryFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (view.getId() == R.id.activityButton) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.flFragments, new InvoicePaymentTabFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public void hideBottomTab(boolean hide){
        if(hide){
            bootomTab.setVisibility(View.GONE);
        }
        else {
            bootomTab.setVisibility(View.VISIBLE);
        }
    }
}
