package com.savitroday.savischools.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.StudentListAdapter;
import com.savitroday.savischools.api.response.Student;
import com.savitroday.savischools.helper.OnItemClickListener;
import com.savitroday.savischools.manager.DashboardManager;
import com.savitroday.savischools.view.fragment.DashboardFragment;
import com.savitroday.savischools.view.fragment.InvoicePaymentFragment;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    
    
    @Inject
    DashboardManager dashboardManager;
    
    RelativeLayout mProgressDialog;
    NavigationView navigationView;
    RecyclerView recyclerView;
    StudentListAdapter studentListAdapter;
    DrawerLayout drawer;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);
        recyclerView = (RecyclerView) hView.findViewById(R.id.studentListRecyclerView);
        
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        
        navigationView.setNavigationItemSelectedListener(this);
        mProgressDialog = (RelativeLayout) findViewById(R.id.progressBar);
        
        Button logout = (Button) findViewById(R.id.logout_button);
        logout.setOnClickListener(this);
        
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack("add");
        
        transaction.add(R.id.flFragments, new DashboardFragment());
        transaction.commit();
    }
    
    public void backpress() {
        
        
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
        
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
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
    
    public void onBackbutton(View v) {
        backpress();
    }
    
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            
            backpress();
        }
    }
    
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        
        if (id == R.id.nav_dashboard) {
            // Handle the camera action
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.flFragments, new DashboardFragment());
            transaction.commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.logout_button) {
            MyApplication.getApp().logout(this);
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
    
    public void onclikthis(View view) {
        Fragment fragment = new InvoicePaymentFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().addToBackStack
                                                                                                         ("hcdbhj");
        fragmentTransaction.add(R.id.flFragments, fragment);
        fragmentTransaction.commit();
    }
}
