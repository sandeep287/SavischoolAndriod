package com.savitroday.savischools.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.api.ApiErrorModel;
import com.savitroday.savischools.api.ApiException;
import com.savitroday.savischools.helper.LoginHelper;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {
    EditText id, uname, pass;
    TextView tview, passlength;
    Button loginbtn;
    static int temp = 0;
    SharedPreferences sharedPreferences;
    
    @Inject
    LoginHelper loginHelper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scloogin_page);
        MyApplication.getApp().getComponent().inject(this);
        
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.top2);
        id = (EditText) findViewById(R.id.Id);
        uname = (EditText) findViewById(R.id.uname);
        pass = (EditText) findViewById(R.id.pass);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        passlength = (TextView) findViewById(R.id.passlenth);
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (pass.getText().toString().trim().length() >= 8) {
                    
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
                //TODO : move this to a validate method and from there call login
                if (id.getText().toString().trim().equals("")) {
                    id.requestFocus();
                    //   id.setHintTextColor(Color.BLACK);
                    id.setError("Enter your id no.");
                    
                    //  Toast.makeText(SCLooginPage.this,"Your Fields is empety",Toast.LENGTH_LONG).show();
                } else if (uname.getText().toString().trim().equals("")) {
                    uname.requestFocus();
                    // uname.setHintTextColor(Color.BLACK);
                    uname.setError("Enter Username");
                    // Toast.makeText(SCLooginPage.this,"Your Fields is empety",Toast.LENGTH_LONG).show();
                    
                } else if (pass.getText().toString().trim().equals("")) {
                    pass.requestFocus();
                    //  pass.setHintTextColor(Color.BLACK);
                    pass.setError("enter your password");
                    //      Toast.makeText(SCLooginPage.this,"Your Fields is empety",Toast.LENGTH_LONG).show();
                    
                } else {
                    if (true) {
                        sharedPreferences = getSharedPreferences("LoginChack", Context.MODE_PRIVATE);
                        final SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("loginverifide", "1");
                        editor.commit();
                    }
                }
                
            }
        });
        
        
    }
    
    void login() {
        //  mProgressDialog.setVisibility(View.VISIBLE);
        loginHelper.setCredentials("manohardaycare78@yopmail.com", "123456", "1");
        loginUser();
    }
    
    public void loginUser() {
        loginHelper.loginAndGetUser().continueWith((task) -> {
                    // mProgressDialog.setVisibility(View.GONE);
                    if (task.getResult() != null) {
                        //UserOAuthResponse profile = (UserOAuthResponse) task.getResult();
                        //TODO - send to mainactivity
                        //getInvoice(profile.schoolid, "");
                        // onNavigationItemSelected(navigationView.getMenu().getItem(0));
                        // navigationView.getMenu().getItem(0).setChecked(true);
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
