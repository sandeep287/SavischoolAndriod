package com.savitroday.savischools.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.SpleshScreen;
import com.savitroday.savischools.adapter.InvoiceAdapter;
import com.savitroday.savischools.api.ApiErrorModel;
import com.savitroday.savischools.api.ApiException;
import com.savitroday.savischools.api.CustomCallAdapter;
import com.savitroday.savischools.api.UserRestService;
import com.savitroday.savischools.api.response.Invoice;
import com.savitroday.savischools.api.response.UserOAuthResponse;
import com.savitroday.savischools.fragment.DashboardFragment;
import com.savitroday.savischools.helper.LoginHelper;
import com.savitroday.savischools.util.Constants;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    
    @Inject
    LoginHelper loginHelper;
    
    @Inject
    UserRestService userRestService;
    
    RelativeLayout mProgressDialog;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyApplication.getApp().getComponent().inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                                                                        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        //toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);
        ImageView drawertoggle = (ImageView) findViewById(R.id.home_screen_header_nav_drawer);
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
        navigationView.setNavigationItemSelectedListener(this);
        mProgressDialog = (RelativeLayout) findViewById(R.id.progressBar);
        login();
    }
    
    void login() {
        mProgressDialog.setVisibility(View.VISIBLE);
        loginHelper.setCredentials("manohardaycare78@yopmail.com", "123456", "1");
        loginUser();
    }
    
    public void loginUser() {
        loginHelper.loginAndGetUser().continueWith((task) -> {
            mProgressDialog.setVisibility(View.GONE);
                    if (task.getResult() != null) {
                        //UserOAuthResponse profile = (UserOAuthResponse) task.getResult();
                        
                        //getInvoice(profile.schoolid, "");
                        onNavigationItemSelected(navigationView.getMenu().getItem(0));
                        navigationView.getMenu().getItem(0).setChecked(true);
                    } else {
                        ApiException e = (ApiException) task.getError();
                        if (e.getKind() == ApiException.Kind.HTTP || e.getKind() == ApiException.Kind.NETWORK) {
                            try {
                                ApiErrorModel apiErrorModel = e.getErrorModel();
                                Toast.makeText(MainActivity.this, apiErrorModel.errorMessage, Toast.LENGTH_LONG).show();
                            } catch (Exception e1) {
                                e1.printStackTrace();
                                Toast.makeText(MainActivity.this,  e.toString(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                    return null;
                }
        );
    }
    
    void getInvoice(String schoolId, String studentId) {
        userRestService.getInvoiceByStudent(schoolId, studentId).enqueue(new CustomCallAdapter
                                                                                     .CustomCallback<List<Invoice>>() {
            
            @Override
            public void success(Response<List<Invoice>> response) {
                if (response.isSuccessful()) {
                    List<Invoice> invoiceList =  response.body();
                    InvoiceAdapter adapter = new InvoiceAdapter(MainActivity.this,invoiceList);
                   // listView.setAdapter(adapter);
                }
            }
            
            @Override
            public void failure(ApiException e) {
                
            }
        });
    }
    
    
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
