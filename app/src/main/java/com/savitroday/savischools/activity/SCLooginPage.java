package com.savitroday.savischools.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.savitroday.savischools.R;

public class SCLooginPage extends AppCompatActivity {
    EditText id,uname,pass;
    TextView tview,passlength;
    Button loginbtn;
   static int temp=0;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scloogin_page);
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.top2);
        id=(EditText)findViewById(R.id.Id);
        uname=(EditText)findViewById(R.id.uname);
        pass=(EditText)findViewById(R.id.pass);
        loginbtn=(Button)findViewById(R.id.loginbtn);
        passlength=(TextView) findViewById(R.id.passlenth);
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (pass.getText().toString().trim().length()>=8)
                {

                    linearLayout.removeView(passlength);
                    temp=1;
                }
                else
                {
                    if (temp==1)
                    {
                        linearLayout.addView(passlength);
                        temp=0;
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
                if(id.getText().toString().trim().equals(""))
                {
                    id.requestFocus();
                    //   id.setHintTextColor(Color.BLACK);
                    id.setError("Enter your id no.");

                    //  Toast.makeText(SCLooginPage.this,"Your Fields is empety",Toast.LENGTH_LONG).show();
                }
                else if (uname.getText().toString().trim().equals(""))
                {
                    uname.requestFocus();
                    // uname.setHintTextColor(Color.BLACK);
                    uname.setError("Enter Username");
                    // Toast.makeText(SCLooginPage.this,"Your Fields is empety",Toast.LENGTH_LONG).show();

                }
                else if (pass.getText().toString().trim().equals(""))
                {
                    pass.requestFocus();
                    //  pass.setHintTextColor(Color.BLACK);
                    pass.setError("enter your password");
                    //      Toast.makeText(SCLooginPage.this,"Your Fields is empety",Toast.LENGTH_LONG).show();

                }

                else
                {
                    if (true)
                    {
                        sharedPreferences =getSharedPreferences("LoginChack", Context.MODE_PRIVATE);
                        final  SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("loginverifide","1");
                        editor.commit();
                    }
                }

            }
        });

    }
}
