package com.savitroday.savischools.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.api.ApiException;
import com.savitroday.savischools.api.CustomCallAdapter;
import com.savitroday.savischools.api.OAuthRestService;
import com.savitroday.savischools.databinding.ActivityGetOtpBinding;
import com.savitroday.savischools.util.AlertUtil;
import com.savitroday.savischools.util.Constants;

import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Response;

public class GetOtpActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityGetOtpBinding mBinding;
    @Inject
    OAuthRestService oAuthRestService;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_get_otp);
        MyApplication.getApp().getComponent().inject(this);
        mBinding.otp0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
                if (mBinding.otp0.getText().length() == 1) {
                    mBinding.otp1.requestFocus();
                    mBinding.otp0.setClickable(false);
                    
                } else {
                    
                    mBinding.otp0.setClickable(true);
                    
                }
            }
        });
        mBinding.otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
                if (mBinding.otp1.getText().length() == 1) {
                    mBinding.otp2.requestFocus();
                    mBinding.otp1.setClickable(false);
                    
                } else {
                    mBinding.otp0.setClickable(true);
                    mBinding.otp0.requestFocus();
                    
                }
            }
        });
        mBinding.otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
                if (mBinding.otp2.getText().length() == 1) {
                    mBinding.otp3.requestFocus();
                    mBinding.otp2.setClickable(false);
                    
                } else {
                    mBinding.otp1.setClickable(true);
                    mBinding.otp1.requestFocus();
                    
                }
            }
        });
        mBinding.otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
                if (mBinding.otp3.getText().length() == 1) {
                    
                    mBinding.otp3.setClickable(true);
                    
                } else {
                    mBinding.otp2.requestFocus();
                    mBinding.otp2.setClickable(true);
                    
                }
            }
        });
        
        mBinding.submit.setOnClickListener(this);
    }
    
    
    void checkotp() {
        String s = mBinding.otp0.getText().toString() + mBinding.otp1.getText().toString() +
                           mBinding.otp2.getText().toString() + mBinding.otp3.getText().toString();
        int otp = 0;
        try {
            otp = Integer.parseInt(s);
            //todo : show progress
            oAuthRestService.verifyOtpCode(MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_EMAIL),
                                           MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_SCHOOL_ID), otp)
                    .enqueue(new CustomCallAdapter.CustomCallback<HashMap<String, String>>() {
                        
                        
                        @Override
                        public void success(Response<HashMap<String, String>> response) {
                        
                        }
                        
                        @Override
                        public void failure(ApiException e) {
                        
                        }
                    });
        } catch (NumberFormatException e) {
            AlertUtil.showSnackbarWithMessage("Invalid input.", mBinding.getRoot());
        }
        
        
    }
    
    @Override
    public void onClick(View view) {
        //todo :validate
        checkotp();
    }
}
