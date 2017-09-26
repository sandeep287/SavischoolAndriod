package com.savitroday.savischools;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.savitroday.savischools.util.Constants;
import com.savitroday.savischools.view.activity.LoginActivity;
import com.savitroday.savischools.view.activity.MainActivity;

public class SpleshScreen extends AppCompatActivity {
    ProgressBar pbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh_screen);
        pbar = (ProgressBar) findViewById(R.id.pbar);
        
        pbar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String temp = MyApplication.tinyDB.getString(Constants.SHARED_PREFERENCES_IS_LOGGED_IN);
                
                
                if (temp.equals("1")) {
                    
                    Intent intent = new Intent(SpleshScreen.this, MainActivity.class);
                    startActivity(intent);
                    pbar.setVisibility(View.INVISIBLE);
                    finish();
                    
                } else if (temp.equals("0")) {
                    Intent intent = new Intent(SpleshScreen.this, LoginActivity.class);
                    startActivity(intent);
                    pbar.setVisibility(View.INVISIBLE);
                    finish();
                }
                
                
            }
        }, 1000);
        
    }
}
