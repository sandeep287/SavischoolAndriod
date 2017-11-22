package com.savitroday.savischools.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.savitroday.savischools.R;
import com.savitroday.savischools.databinding.ActivityGetOtpBinding;

public class GetOtpActivity extends AppCompatActivity {
ActivityGetOtpBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_otp);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_get_otp);
        mBinding.otp0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (mBinding.otp0.getText().length()==1)
                {
                    mBinding.otp1.requestFocus();
                    mBinding.otp0.setClickable(false);

                }
                else
                {

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
                if (mBinding.otp1.getText().length()==1)
                {
                    mBinding.otp2.requestFocus();
                    mBinding.otp1.setClickable(false);

                }
                else
                {
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
                if (mBinding.otp2.getText().length()==1)
                {
                    mBinding.otp3.requestFocus();
                    mBinding.otp2.setClickable(false);

                }
                else
                {
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
                if (mBinding.otp3.getText().length()==1)
                {

                    mBinding.otp3.setClickable(true);

                }
                else
                {
                    mBinding.otp2.requestFocus();
                    mBinding.otp2.setClickable(true);

                }
            }
        });
    }
}
