package com.savitroday.savischools;

import android.content.Intent;
import android.os.Bundle;
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
        
        // pbar.setVisibility(View.VISIBLE);
        
        Intent intent;
        if (MyApplication.tinyDB.getBoolean(Constants.SHARED_PREFERENCES_IS_LOGGED_IN, false)) {
            intent = new Intent(SpleshScreen.this, MainActivity.class);
        } else {
            intent = new Intent(SpleshScreen.this, LoginActivity.class);
        }
        startActivity(intent);
       // pbar.setVisibility(View.INVISIBLE);
        finish();
    }
    
    

}
