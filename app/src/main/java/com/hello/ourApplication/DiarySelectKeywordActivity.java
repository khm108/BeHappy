package com.hello.ourApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DiarySelectKeywordActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton keywordButton1, keywordButton2, keywordButton3, keywordButton4, keywordButton5, keywordButton6, keywordButton7, keywordButton8, keywordButton9;
    boolean isButton1, isButton2, isButton3, isButton4, isButton5, isButton6, isButton7, isButton8, isButton9 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_select_keyword);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 왼쪽 상단 버튼 만들기
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_slide_icon); //왼쪽 상단 버튼 아이콘 지정

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        TextView dateTextView = findViewById(R.id.dateTextView);

        // 현재 날짜를 가져옴
        Calendar calendar = Calendar.getInstance();

        // 날짜를 원하는 형식으로 포맷팅
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd (E)", Locale.getDefault());
        String formattedDate = formatter.format(calendar.getTime());

        // TextView에 날짜 표시
        dateTextView.setText(formattedDate);

        keywordButton1 = findViewById(R.id.keyword_button_1);
        keywordButton2 = findViewById(R.id.keyword_button_2);
        keywordButton3 = findViewById(R.id.keyword_button_3);
        keywordButton4 = findViewById(R.id.keyword_button_4);
        keywordButton5= findViewById(R.id.keyword_button_5);
        keywordButton6 = findViewById(R.id.keyword_button_6);
        keywordButton7 = findViewById(R.id.keyword_button_7);
        keywordButton8 = findViewById(R.id.keyword_button_8);
        keywordButton9 = findViewById(R.id.keyword_button_9);

        // 초기 버튼 설정
        keywordButton1.setImageResource(R.drawable.keyword_button_1);
        keywordButton2.setImageResource(R.drawable.keyword_button_1);
        keywordButton3.setImageResource(R.drawable.keyword_button_1);
        keywordButton4.setImageResource(R.drawable.keyword_button_1);
        keywordButton5.setImageResource(R.drawable.keyword_button_1);
        keywordButton6.setImageResource(R.drawable.keyword_button_1);
        keywordButton7.setImageResource(R.drawable.keyword_button_1);
        keywordButton8.setImageResource(R.drawable.keyword_button_1);
        keywordButton9.setImageResource(R.drawable.keyword_button_1);

        keywordButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isButton1) {
                    // 버튼 상태가 1일 때 눌렸을 경우
                    keywordButton1.setImageResource(R.drawable.keyword_button_2);
                } else {
                    // 버튼 상태가 2일 때 눌렸을 경우
                    keywordButton1.setImageResource(R.drawable.keyword_button_1);
                }

                // 버튼 상태 변경
                isButton1 = !isButton1;
            }
        });

        keywordButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isButton2) {
                    // 버튼 상태가 1일 때 눌렸을 경우
                    keywordButton2.setImageResource(R.drawable.keyword_button_2);
                } else {
                    // 버튼 상태가 2일 때 눌렸을 경우
                    keywordButton2.setImageResource(R.drawable.keyword_button_1);
                }

                // 버튼 상태 변경
                isButton2 = !isButton2;
            }
        });

        keywordButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isButton3) {
                    // 버튼 상태가 1일 때 눌렸을 경우
                    keywordButton3.setImageResource(R.drawable.keyword_button_2);
                } else {
                    // 버튼 상태가 2일 때 눌렸을 경우
                    keywordButton3.setImageResource(R.drawable.keyword_button_1);
                }

                // 버튼 상태 변경
                isButton3 = !isButton3;
            }
        });

        keywordButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isButton4) {
                    // 버튼 상태가 1일 때 눌렸을 경우
                    keywordButton4.setImageResource(R.drawable.keyword_button_2);
                } else {
                    // 버튼 상태가 2일 때 눌렸을 경우
                    keywordButton4.setImageResource(R.drawable.keyword_button_1);
                }

                // 버튼 상태 변경
                isButton4 = !isButton4;
            }
        });

        keywordButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isButton5) {
                    // 버튼 상태가 1일 때 눌렸을 경우
                    keywordButton5.setImageResource(R.drawable.keyword_button_2);
                } else {
                    // 버튼 상태가 2일 때 눌렸을 경우
                    keywordButton5.setImageResource(R.drawable.keyword_button_1);
                }

                // 버튼 상태 변경
                isButton5 = !isButton5;
            }
        });

        keywordButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isButton6) {
                    // 버튼 상태가 1일 때 눌렸을 경우
                    keywordButton6.setImageResource(R.drawable.keyword_button_2);
                } else {
                    // 버튼 상태가 2일 때 눌렸을 경우
                    keywordButton6.setImageResource(R.drawable.keyword_button_1);
                }

                // 버튼 상태 변경
                isButton6 = !isButton6;
            }
        });

        keywordButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isButton7) {
                    // 버튼 상태가 1일 때 눌렸을 경우
                    keywordButton7.setImageResource(R.drawable.keyword_button_2);
                } else {
                    // 버튼 상태가 2일 때 눌렸을 경우
                    keywordButton7.setImageResource(R.drawable.keyword_button_1);
                }

                // 버튼 상태 변경
                isButton7 = !isButton7;
            }
        });

        keywordButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isButton8) {
                    // 버튼 상태가 1일 때 눌렸을 경우
                    keywordButton8.setImageResource(R.drawable.keyword_button_2);
                } else {
                    // 버튼 상태가 2일 때 눌렸을 경우
                    keywordButton8.setImageResource(R.drawable.keyword_button_1);
                }

                // 버튼 상태 변경
                isButton8 = !isButton8;
            }
        });

        keywordButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isButton9) {
                    // 버튼 상태가 1일 때 눌렸을 경우
                    keywordButton9.setImageResource(R.drawable.keyword_button_2);
                } else {
                    // 버튼 상태가 2일 때 눌렸을 경우
                    keywordButton9.setImageResource(R.drawable.keyword_button_1);
                }

                // 버튼 상태 변경
                isButton9 = !isButton9;
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_chat: // "채팅하기" 메뉴 클릭 시
                        Intent intent = new Intent(DiarySelectKeywordActivity.this, ChatMainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item_recommend: // "추천받기" 메뉴 클릭 시
                        intent = new Intent(DiarySelectKeywordActivity.this, RecommendActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item_diary: // "일기 모아보기" 메뉴 클릭 시
                        intent = new Intent(DiarySelectKeywordActivity.this, DiaryMainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item_test: // "우울증 자가 진단" 메뉴 클릭 시
                        intent = new Intent(DiarySelectKeywordActivity.this, TestActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item_diary_new: // "일기 작성하기" 메뉴 클릭 시
                        intent = new Intent(DiarySelectKeywordActivity.this, DiaryWriteActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item_checklist:
                        intent = new Intent(DiarySelectKeywordActivity.this, TodoMainActivity.class);
                        startActivity(intent);
                        break;
                }

                // 네비게이션 드로어 닫기
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);

        // BottomNavigationView의 아이템 클릭 리스너 설정
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_bar_home:
                    // 홈 버튼 클릭 시
                    startActivity(new Intent(DiarySelectKeywordActivity.this, MainActivity.class));
                    return true;
                case R.id.menu_bar_chat:
                    // 채팅 버튼 클릭 시
                    startActivity(new Intent(DiarySelectKeywordActivity.this, ChatMainActivity.class));
                    return true;
                case R.id.menu_bar_calendar:
                    // 캘린더 버튼 클릭 시
                    startActivity(new Intent(DiarySelectKeywordActivity.this, CalendarActivity.class));
                    return true;
                default:
                    return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { // 왼쪽 상단 버튼 눌렀을 때
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() { // 뒤로 가기 했을 때
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
