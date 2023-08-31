package com.hello.ourApplication;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class TutorialActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    ViewPagerAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_tutorial);

        viewPager2 = findViewById(R.id.viewpager);
        vpAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(vpAdapter);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
    }
}
