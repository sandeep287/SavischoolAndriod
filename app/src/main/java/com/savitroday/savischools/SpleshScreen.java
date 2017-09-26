package com.savitroday.savischools;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.savitroday.savischools.activity.MainActivity;
import com.savitroday.savischools.activity.SCLooginPage;

public class SpleshScreen extends AppCompatActivity {
    ProgressBar pbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh_screen);
        pbar=(ProgressBar)findViewById(R.id.pbar);

        pbar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences=getSharedPreferences("LoginChack", Context.MODE_PRIVATE);

                String temp=sharedPreferences.getString("loginverifide","0");

                if(temp.equals("1"))
                {

                    Intent intent=new Intent(SpleshScreen.this, MainActivity.class);
                    startActivity(intent);
                    pbar.setVisibility(View.INVISIBLE);
                    finish();

                }
                else if(temp.equals("0"))
                {
                    Intent intent=new Intent(SpleshScreen.this, SCLooginPage.class);
                    startActivity(intent);
                    pbar.setVisibility(View.INVISIBLE);
                    finish();
                }


            }
        },1000);

    }
}
