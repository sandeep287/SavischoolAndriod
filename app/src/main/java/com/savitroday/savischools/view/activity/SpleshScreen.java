package com.savitroday.savischools.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.savitroday.savischools.MyApplication;
import com.savitroday.savischools.R;
import com.savitroday.savischools.util.Constants;

public class SpleshScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh_screen);

        Intent intent;
        if (MyApplication.tinyDB.getBoolean(Constants.SHARED_PREFERENCES_IS_LOGGED_IN, false)) {
            intent = new Intent(SpleshScreen.this, MainActivity.class);
        } else {
            intent = new Intent(SpleshScreen.this, LoginActivity.class);
        }
        startActivity(intent);

        finish();
    }
    
    

}
