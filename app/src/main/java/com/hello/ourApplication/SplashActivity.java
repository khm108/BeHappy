package com.hello.ourApplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3000; // 스플래시 화면이 보여지는 시간(3초)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, com.hello.ourApplication.MainActivity.class); // 스플래시 화면이 끝나면 이동할 Activity
                startActivity(intent);
                finish(); // 현재 Activity 종료
            }
        }, SPLASH_TIME_OUT);
    }
}