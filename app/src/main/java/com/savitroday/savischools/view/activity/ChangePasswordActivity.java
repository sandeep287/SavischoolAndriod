package com.savitroday.savischools.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.api.OAuthRestService;
import com.savitroday.savischools.databinding.ActivityChangePasswordBinding;
import com.savitroday.savischools.databinding.ActivityForgotPasswordBinding;

import javax.inject.Inject;

public class ChangePasswordActivity extends AppCompatActivity {

    ActivityChangePasswordBinding mBinding;
    @Inject
    OAuthRestService oAuthRestService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_change_password);
        MyApplication.getApp().getComponent().inject(this);
    }
    
    
}
