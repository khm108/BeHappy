package com.hello.ourApplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

//    long now = System.currentTimeMillis(); //현재시간 가져오기
//    Date date = new Date(now); //Date 형식으로 Convert
//
//    Calendar cal = Calendar.getInstance();
//    cal.setTime(date);
//
//    int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
//    String str_week = "";
//    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd ()");
//    String formatDate = format.format(date);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 왼쪽 상단 버튼 만들기
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_slide_icon); //왼쪽 상단 버튼 아이콘 지정

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.navigation_view);

        TextView dateTextView = findViewById(R.id.dateTextView);
        TextView plannedTasksTextView = findViewById(R.id.plannedTasksTextView);
        TextView completedTasksTextView = findViewById(R.id.completedTasksTextView);

        // 현재 날짜를 가져옴
        Calendar calendar = Calendar.getInstance();

        // 날짜를 원하는 형식으로 포맷팅
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd (E)", Locale.getDefault());
        String formattedDate = formatter.format(calendar.getTime());

        // TextView에 날짜 표시
        dateTextView.setText(formattedDate);

        // 계획한 일 개수 출력
        int numberOfPlannedTasks = 5; // 계획한 할 일 개수
        String newText1 = "♥ 오늘 계획한 할 일: " + numberOfPlannedTasks + " 개";
        plannedTasksTextView.setText(newText1);

        // 완료한 일 개수 출력
        int numberOfCompletedTasks = 5; // 완료한 할 일 개수
        String newText2 = "♥ 오늘 완료한 할 일: " + numberOfCompletedTasks + " 개";
        completedTasksTextView.setText(newText2);




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 왼쪽 상단 버튼 눌렀을 때
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() { //뒤로가기 했을 때
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}