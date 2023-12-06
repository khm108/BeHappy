package com.hello.ourApplication.Todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.hello.ourApplication.Chat.ChatMainActivity;
import com.hello.ourApplication.Diary.DiaryMainActivity;
import com.hello.ourApplication.Diary.DiaryWriteActivity;
import com.hello.ourApplication.MainActivity;
import com.hello.ourApplication.R;
import com.hello.ourApplication.RecommendActivity;
import com.hello.ourApplication.TestActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

public class TodoMainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    private Button addNotificationButton;
    private LinearLayout popupLayout;
    private EditText alarmName;
    private EditText alarmMemo;

    private TimePicker timePicker;
    private DatePicker datePicker;
    private Button setAlarmButton;
    private ListView alarmListView;

    private ArrayList<TodoAlarm> alarmList = new ArrayList<>();
    private int alarmIdCounter = 0;
    private ArrayAdapter<TodoAlarm> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 왼쪽 상단 버튼 만들기
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_slide_icon); //왼쪽 상단 버튼 아이콘 지정

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        ImageButton todoAddButton = findViewById(R.id.todo_add);
        popupLayout = findViewById(R.id.popupLayout);
        alarmName = findViewById(R.id.alarmName);
        alarmMemo = findViewById(R.id.alarmMemo);

        /*
        // "알림 추가" 버튼 클릭 시 팝업 표시
        todoAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupLayout.setVisibility(View.VISIBLE);
            }
        });

        // 팝업에서 확인 버튼 클릭 시 알림 추가 코드 작성
        Button confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 이름(nameEditText.getText()), 설명(descriptionEditText.getText()), 시간 등을 사용하여 알림 추가
                // 추가 코드를 작성하세요.
                // 예: addNotification(nameEditText.getText().toString(), descriptionEditText.getText().toString(), ...);

                // 팝업 닫기
                popupLayout.setVisibility(View.GONE);
            }
        });

        // 팝업에서 취소 버튼 클릭 시 팝업 닫기
        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupLayout.setVisibility(View.GONE);
            }
        });

         */

        timePicker = findViewById(R.id.timePicker);
        datePicker = findViewById(R.id.datePicker);
        setAlarmButton = findViewById(R.id.confirmButton);
        alarmListView = findViewById(R.id.alarmListView);

        adapter = new TodoAlarmAdapter(this, alarmList);
        alarmListView.setAdapter(adapter);

        alarmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toggleAlarm(position);
            }
        });

        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarm();
            }
        });

        // bottom sheet 부분
        BottomSheetDialog bottomSheetDialog;

        View bottomSheetView = getLayoutInflater().inflate(R.layout.todo_bottom_sheet, null);
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(bottomSheetView);

        // "알림 추가" 버튼 클릭하면 아래에서 올라오도록
        todoAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();
            }
        });

        Button confirmButton = bottomSheetDialog.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarm();
                // 이름(nameEditText.getText()), 설명(descriptionEditText.getText()), 시간 등을 사용하여 알림 추가
                // 추가 코드를 작성하세요.
                // 예: addNotification(nameEditText.getText().toString(), descriptionEditText.getText().toString(), ...);

                // 팝업 닫기
                bottomSheetDialog.dismiss();
            }
        });

        Button cancelButton = bottomSheetDialog.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_chat: // "채팅하기" 메뉴 클릭 시
                        Intent intent = new Intent(TodoMainActivity.this, ChatMainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item_recommend: // "추천받기" 메뉴 클릭 시
                        intent = new Intent(TodoMainActivity.this, RecommendActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item_diary: // "일기 모아보기" 메뉴 클릭 시
                        intent = new Intent(TodoMainActivity.this, DiaryMainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item_test: // "우울증 자가 진단" 메뉴 클릭 시
                        intent = new Intent(TodoMainActivity.this, TestActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item_diary_new: // "일기 작성하기" 메뉴 클릭 시
                        intent = new Intent(TodoMainActivity.this, DiaryWriteActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item_checklist:
                        intent = new Intent(TodoMainActivity.this, TodoMainActivity.class);
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
                    startActivity(new Intent(this, MainActivity.class));
                    return true;
                case R.id.menu_bar_chat:
                    // 채팅 버튼 클릭 시
                    startActivity(new Intent(this, ChatMainActivity.class));
                    return true;
                case R.id.menu_bar_calendar:
                    // 캘린더 버튼 클릭 시
                    startActivity(new Intent(this, CalendarActivity.class));
                    return true;
                default:
                    return false;
            }
        });
    }

    public void onToolbarMainButtonClick(View view) { // 오른쪽 상단 홈 버튼
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void toggleAlarm(int position) {
        if (position >= 0 && position < alarmList.size()) {
            TodoAlarm alarm = alarmList.get(position);

            boolean isAlarmOn = alarm.isAlarmOn();
            alarm.setAlarmOn(!isAlarmOn);
            adapter.notifyDataSetChanged();

            if (isAlarmOn) {
                // 알람 끄기
                cancelAlarm(alarm);
                Toast.makeText(this, "알람이 꺼졌습니다.", Toast.LENGTH_SHORT).show();
            } else {
                // 알람 켜기
                setSingleAlarm(alarm);
                Toast.makeText(this, "알람이 켜졌습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setAlarm() {
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();
        String name = alarmName.getText().toString();
        String memo = alarmMemo.getText().toString();

        TodoAlarm alarm = new TodoAlarm(alarmIdCounter++, year, month, day, hour, minute, name);
        alarmList.add(alarm);
        adapter.notifyDataSetChanged();

        String alarmTime = String.format("%02d:%02d on %d/%02d/%02d", hour, minute, year, month + 1, day);
        Toast.makeText(this, "알람이 " + alarmTime + "에 설정되었습니다.\n알림 이름: " + name, Toast.LENGTH_SHORT).show();

        setSingleAlarm(alarm);
    }

    private void setSingleAlarm(TodoAlarm alarm) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, TodoAlarmReceiver.class);
        intent.putExtra("alarmId", alarm.getId());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, alarm.getId(), intent, PendingIntent.FLAG_IMMUTABLE);
        long timeInMillis = alarm.getTimeInMillis();
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);
    }

    private void cancelAlarm(TodoAlarm alarm) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, TodoAlarmReceiver.class);
        intent.putExtra("alarmId", alarm.getId());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, alarm.getId(), intent, PendingIntent.FLAG_IMMUTABLE);

        alarmManager.cancel(pendingIntent);
        adapter.notifyDataSetChanged();
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

    private void hideBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setVisibility(View.GONE);
    }

    private void showBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }
}