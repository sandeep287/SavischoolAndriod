package com.savitroday.savischools.view.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.SpleshScreen;
import com.savitroday.savischools.api.ApiErrorModel;
import com.savitroday.savischools.api.ApiException;
import com.savitroday.savischools.api.response.UserOAuthResponse;
import com.savitroday.savischools.helper.LoginHelper;
import com.savitroday.savischools.util.Constants;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {
    EditText schoolId, userName, password;
    TextView tview, passlength;
    Button loginbtn;

    static int temp = 0;
    RelativeLayout relativeLayout;
    SharedPreferences sharedPreferences;
    RelativeLayout progressBar;

    @Inject
    LoginHelper loginHelper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scloogin_page);
        MyApplication.getApp().getComponent().inject(this);
     progressBar =(RelativeLayout)findViewById(R.id.progressBar);


        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.top2);
        schoolId = (EditText) findViewById(R.id.Id);
        userName = (EditText) findViewById(R.id.uname);
        password = (EditText) findViewById(R.id.pass);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        passlength = (TextView) findViewById(R.id.passlenth);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (password.getText().toString().trim().length() >= 8) {
                    
                    linearLayout.removeView(passlength);
                    temp = 1;
                } else {
                    if (temp == 1) {
                        linearLayout.addView(passlength);
                        temp = 0;
                    }
                }
                
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
                
            }
        });
        
        
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {
                           progressBar.setVisibility(View.VISIBLE);
                           login();



                }
            }
        });
        
        
    }
    
    boolean validate() {
        if (schoolId.getText().toString().trim().equals("") ||
                    userName.getText().toString().trim().equals("") ||
                    password.getText().toString().trim().equals("")) {
            if (schoolId.getText().toString().trim().equals("")) {
                schoolId.requestFocus();
                //   schoolId.setHintTextColor(Color.BLACK);
                schoolId.setError("Enter your schoolId no.");
                
                //  Toast.makeText(SCLooginPage.this,"Your Fields is empety",Toast.LENGTH_LONG).show();
            } else if (userName.getText().toString().trim().equals("")) {
                userName.requestFocus();
                // userName.setHintTextColor(Color.BLACK);
                userName.setError("Enter Username");
                // Toast.makeText(SCLooginPage.this,"Your Fields is empety",Toast.LENGTH_LONG).show();
                
            } else if (password.getText().toString().trim().equals("")) {
                password.requestFocus();
                //  password.setHintTextColor(Color.BLACK);
                password.setError("enter your password");
                //      Toast.makeText(SCLooginPage.this,"Your Fields is empety",Toast.LENGTH_LONG).show();
                
            }
            return false;
        }
        return true;
    }
    
    void login() {
        //  mProgressDialog.setVisibility(View.VISIBLE);
        loginHelper.setCredentials(userName.getText().toString().trim(), password.getText().toString().trim(),
                schoolId.getText().toString().trim());

        loginHelper.setCredentials("singhs", "123456", "3");

        loginUser();
    }
    
    public void loginUser() {
        loginHelper.loginAndGetUser().continueWith((task) -> {
            progressBar.setVisibility(View.GONE);
                    // mProgressDialog.setVisibility(View.GONE);
                    if (task.getResult() != null) {

                        MyApplication.tinyDB.putBoolean(Constants.SHARED_PREFERENCES_IS_LOGGED_IN,true);
                        UserOAuthResponse profile = (UserOAuthResponse) task.getResult();
                        if(profile.userType.equals("SchoolParent")) {

                            Intent intent = new Intent(LoginActivity.this, TabbedActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            new AlertDialog.Builder(LoginActivity.this)
                                    //set message, mTitle, and icon
                                    .setCancelable(false)
                                    .setMessage("You have successfully logged in. Usertype is " + profile.userType)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            dialog.dismiss();
                                        }
                                    }).create().show();
                        }
                    } else {

                        ApiException e = (ApiException) task.getError();
                        if (e.getKind() == ApiException.Kind.HTTP || e.getKind() == ApiException.Kind.NETWORK) {

                            try {
                                ApiErrorModel apiErrorModel = e.getErrorModel();
                                Toast.makeText(LoginActivity.this, apiErrorModel.errorMessage, Toast.LENGTH_LONG)
                                        .show();
                            } catch (Exception e1) {
                                e1.printStackTrace();
                                Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        } else {

                            Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                    return null;
                }
        );
    }
    
}
