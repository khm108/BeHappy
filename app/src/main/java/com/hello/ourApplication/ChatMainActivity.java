package com.hello.ourApplication;

import android.content.Intent;
import android.database.DataSetObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ChatMainActivity extends AppCompatActivity{
    private static final String TAG = "ChatActivity";
    ChatMessageAdapter chatMessageAdapter;
    private ListView listView;
    private EditText chatText;
    private ImageButton buttonSend;

    Intent intent;
    Toolbar toolbar; // 왼쪽상단 툴바
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private boolean side = false;  // 대화 창 오른쪽/왼쪽 배치 여부를 나타내는 플래그

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        setContentView(R.layout.chat_main); // chat_main 레이아웃을 화면에 설정

        // 툴바
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

        buttonSend = (ImageButton) findViewById(R.id.buttonSend); // 버튼 위젯을 찾음

        listView = (ListView) findViewById(R.id.chat_listView); // 대화 목록을 표시할 ListView 위젯을 찾음

        chatMessageAdapter = new ChatMessageAdapter(getApplicationContext(), R.layout.chatting_message);
        listView.setAdapter(chatMessageAdapter); // ListView에 대화 메시지를 표시하기 위한 어댑터 설정

        chatText = (EditText) findViewById(R.id.chatText); // 사용자 입력을 받을 EditText 위젯을 찾음
        chatText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    sendChatMessage(); // 엔터 키를 누르면 채팅 메시지를 보내도록 설정
                    return sendChatMessage();
                }
                return false;
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
                sendChatMessage();
            } // "보내기" 버튼을 클릭하면 채팅 메시지를 보내도록 설정
        });

        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL); // ListView가 항상 아래로 스크롤되도록 설정
        listView.setAdapter(chatMessageAdapter);

        //to scroll the list view to bottom on data change
        chatMessageAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatMessageAdapter.getCount() - 1); // 데이터가 변경될 때마다 ListView를 가장 아래로 스크롤
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_recommend: // "추천받기" 메뉴 클릭 시
                        Intent intent = new Intent(ChatMainActivity.this, RecommendActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item_diary: // "일기 모아보기" 메뉴 클릭 시
                        intent = new Intent(ChatMainActivity.this, DiaryMainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item_test: // "우울증 자가 진단" 메뉴 클릭 시
                        intent = new Intent(ChatMainActivity.this, TestActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item_diary_new: // "일기 작성하기" 메뉴 클릭 시
                        intent = new Intent(ChatMainActivity.this, DiaryWriteActivity.class);
                        startActivity(intent);
                        break;
                }

                // 네비게이션 드로어 닫기
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
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

    private boolean sendChatMessage() {
        if(side == false){
            chatMessageAdapter.add(new ChatMessage(side, chatText.getText().toString())); // 대화 메시지 어댑터에 메시지 추가
            chatText.setText(""); // 입력 필드 초기화
            side = !side; // 대화창 오른쪽/왼쪽 배치를 번갈아가며 변경
        }
        else if(side == true){
            chatMessageAdapter.add(new ChatMessage(side, "기분이 좋지 않을땐 쉬는것도 좋아")); // 대화 메시지 어댑터에 메시지 추가
            chatText.setText(""); // 입력 필드 초기화
            side = !side; // 대화창 오른쪽/왼쪽 배치를 번갈아가며 변경
        }
        return true;
    }
}
