package com.wiztech.taxation_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.wiztech.taxation_app.R;

public class SplashActivity extends AppCompatActivity {
    int i = 0;
    private RelativeLayout rlRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rlRedirect = findViewById(R.id.rlRedirect);


        rlRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (LoginSession.getLoggedStatus(getApplicationContext())) {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                }
                finish();
                startActivity(intent);
            }
        });

//        final long period = 15;
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                //this repeats every 100 ms
//                if (i < 250) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            // textView.setText(String.valueOf(i)+"%");
//                        }
//                    });
//                    // progressBar.setProgress(i);
//                    i++;
//                } else {
//                    //closing the timer
//                    timer.cancel();
//                    Intent intent ;
//                    if (LoginSession.getLoggedStatus(getApplicationContext())){
//                        intent=new Intent(SplashActivity.this, MainActivity.class);
//                    }else{
//                        intent=new Intent(SplashActivity.this, LoginActivity.class);
//                    }
//                    startActivity(intent);
//                    // close this activity
//                    finish();
//                }
//            }
//        }, 0, period);
    }
}