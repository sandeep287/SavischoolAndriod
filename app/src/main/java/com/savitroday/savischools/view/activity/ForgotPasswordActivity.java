package com.savitroday.savischools.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.api.ApiException;
import com.savitroday.savischools.api.CustomCallAdapter;
import com.savitroday.savischools.api.OAuthRestService;
import com.savitroday.savischools.databinding.ActivityForgotPasswordBinding;
import com.savitroday.savischools.util.AlertUtil;

import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    
    @Inject
    OAuthRestService oAuthRestService;
    ActivityForgotPasswordBinding mBinding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        MyApplication.getApp().getComponent().inject(this);
        mBinding.resetPassword.setOnClickListener(this);
    }
    
    void forgotPassword() {
        //todo : show progressbar
        oAuthRestService.validateEmail(mBinding.mailid.getText().toString().trim(), mBinding.schoolid.getText()
                                                                                            .toString().trim())
                .enqueue(new CustomCallAdapter.CustomCallback<HashMap<String, String>>() {
                    @Override
                    public void success(Response<HashMap<String, String>> response) {
                        
                        
                        new AlertDialog.Builder(ForgotPasswordActivity.this)
                                .setCancelable(false)
                                .setMessage("Please check your email for otp.")
                                .setPositiveButton("OK",
                                                   (dialog, whichButton) -> {
                                                       dialog.dismiss();
                                                       Intent i = new Intent(ForgotPasswordActivity.this,
                                                                             GetOtpActivity.class);
                                                       i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                       ForgotPasswordActivity.this.startActivity(i);
                                                   }).create().show();
                    }
                    
                    @Override
                    public void failure(ApiException e) {
                        AlertUtil.showSnackbarWithMessage(e.getMessage(),mBinding.getRoot());
                    }
                });
    }
    
    @Override
    public void onClick(View view) {
        //todo : validate empty strings and email address
        forgotPassword();
    }
}
