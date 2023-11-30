package com.hello.ourApplication;

import android.content.Intent;
import android.database.DataSetObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Message;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatMainActivity extends AppCompatActivity{
    Intent intent;
    Toolbar toolbar; // 왼쪽상단 툴바
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    RecyclerView recycler_view;
    EditText et_msg;
    ImageButton btn_send;

    List<ChatMessage> messageList;
    ChatMessageAdapter messageAdapter;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client;

    private static final String MY_SECRET_KEY = "******";

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

//        buttonSend = (ImageButton) findViewById(R.id.buttonSend); // 버튼 위젯을 찾음
//
//        listView = (ListView) findViewById(R.id.chat_listView); // 대화 목록을 표시할 ListView 위젯을 찾음
//
//        chatMessageAdapter = new ChatMessageAdapter(getApplicationContext(), R.layout.chatting_message);
//        listView.setAdapter(chatMessageAdapter); // ListView에 대화 메시지를 표시하기 위한 어댑터 설정
//
//        chatText = (EditText) findViewById(R.id.chatText); // 사용자 입력을 받을 EditText 위젯을 찾음
//        chatText.setOnKeyListener(new View.OnKeyListener() {
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                    sendChatMessage(); // 엔터 키를 누르면 채팅 메시지를 보내도록 설정
//                    return sendChatMessage();
//                }
//                return false;
//            }
//        });
//
//        buttonSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                sendChatMessage();
//                sendChatMessage();
//            } // "보내기" 버튼을 클릭하면 채팅 메시지를 보내도록 설정
//        });
//
//        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL); // ListView가 항상 아래로 스크롤되도록 설정
//        listView.setAdapter(chatMessageAdapter);
//
//        //to scroll the list view to bottom on data change
//        chatMessageAdapter.registerDataSetObserver(new DataSetObserver() {
//            @Override
//            public void onChanged() {
//                super.onChanged();
//                listView.setSelection(chatMessageAdapter.getCount() - 1); // 데이터가 변경될 때마다 ListView를 가장 아래로 스크롤
//            }
//        });

        recycler_view = findViewById(R.id.recycler_view);
        et_msg = findViewById(R.id.et_msg);
        btn_send = findViewById(R.id.btn_send);

        recycler_view.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setStackFromEnd(true);
        recycler_view.setLayoutManager(manager);

        messageList = new ArrayList<>();
        messageAdapter = new ChatMessageAdapter(messageList);
        recycler_view.setAdapter(messageAdapter);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = et_msg.getText().toString().trim();
                addToChat(question, ChatMessage.SENT_BY_ME);
                et_msg.setText("");
                callAPI(question);
            }
        });

        //연결시간 설정. 60초/120초/60초
        client = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

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

        void addToChat(String message, String sentBy){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    messageList.add(new ChatMessage(message, sentBy));
                    messageAdapter.notifyDataSetChanged();
                    recycler_view.smoothScrollToPosition(messageAdapter.getItemCount());
                }
            });
        }

        void addResponse(String response){
            messageList.remove(messageList.size()-1);
            addToChat(response, ChatMessage.SENT_BY_BOT);
        }

    void callAPI(String question){
        //okhttp
        messageList.add(new ChatMessage("...", ChatMessage.SENT_BY_BOT));


        JSONArray arr = new JSONArray();
        JSONObject baseAi = new JSONObject();
        JSONObject userMsg = new JSONObject();

        try {
            // gpt api playground 설정
            baseAi.put("role", "user");
            // baseAi.put("content", "You are a helpful and kind AI Assistant.");
            baseAi.put("content", "우울한 사람들에게 상담을 제공하는 상담사. 짧게 대답해줘.");

            //유저 메세지
            userMsg.put("role", "user");
            userMsg.put("content", question);
            //array로 한번에 보내기
            arr.put(baseAi);
            arr.put(userMsg);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JSONObject object = new JSONObject();
        try {
            object.put("model", "gpt-3.5-turbo");
            object.put("messages", arr);
            object.put("max_tokens", 100);
            object.put("top_p", 1);
            object.put("frequency_penalty", 0.5);
            object.put("presence_penalty", 0.5);

        } catch (JSONException e){
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(object.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer " + MY_SECRET_KEY)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                addResponse("Failed to load response due to "+e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        //아래 result 받아오는 경로 수정
                        String result = jsonArray.getJSONObject(0).getJSONObject("message").getString("content");
                        addResponse(result.trim());
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                } else {
                    addResponse("Failed to load response due to "+response.body().string());
                }
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


//    private boolean sendChatMessage() {
//        if(side == false){
//            chatMessageAdapter.add(new ChatMessage(side, chatText.getText().toString())); // 대화 메시지 어댑터에 메시지 추가
//            chatText.setText(""); // 입력 필드 초기화
//            side = !side; // 대화창 오른쪽/왼쪽 배치를 번갈아가며 변경
//        }
//        else if(side == true){
//            chatMessageAdapter.add(new ChatMessage(side, "기분이 좋지 않을땐 쉬는것도 좋아")); // 대화 메시지 어댑터에 메시지 추가
//            chatText.setText(""); // 입력 필드 초기화
//            side = !side; // 대화창 오른쪽/왼쪽 배치를 번갈아가며 변경
//        }
//        return true;
//    }
}
