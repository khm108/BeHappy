package com.hello.ourApplication.Tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.hello.ourApplication.Registration.LoginActivity;
import com.hello.ourApplication.R;
import com.hello.ourApplication.ViewPagerAdapter;

public class TutorialActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_tutorial);

        viewPager2 = findViewById(R.id.viewpager);
        ImageButton buttonLogin = findViewById(R.id.tut_button_login);
        ImageButton buttonNext = findViewById(R.id.tut_button_next);

        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (position == 5) { // FragmentTutorial6의 위치 (0부터 시작)
                    buttonLogin.setVisibility(View.GONE);
                    buttonNext.setVisibility(View.GONE);
                } else {
                    buttonLogin.setVisibility(View.VISIBLE);
                    buttonNext.setVisibility(View.VISIBLE);
                }
                super.onPageSelected(position);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TutorialActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = viewPager2.getCurrentItem();
                if (currentPosition < viewPagerAdapter.getItemCount() - 1) {
                    viewPager2.setCurrentItem(currentPosition + 1);
                }
            }
        });
    }
}