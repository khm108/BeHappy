package com.hello.ourApplication;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
public class ChatMessageAdapter extends ArrayAdapter {
    private TextView chatText;
    private List chatMessageList = new ArrayList<ChatMessage>();
    private LinearLayout singleMessageContainer;

    // ChatMessage 객체를 어댑터에 추가
    public void add(@Nullable ChatMessage object) {
        chatMessageList.add(object);
        super.add(object);
    }

    // 생성자
    public ChatMessageAdapter(@NonNull Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    // 어댑터에 있는 채팅 메시지의 개수 반환
    @Override
    public int getCount() {
        return this.chatMessageList.size();
    }

    // 지정된 인덱스에 있는 채팅 메시지 객체 반환
    @Nullable
    @Override
    public ChatMessage getItem(int index) {
        return (ChatMessage) this.chatMessageList.get(index);
    }

    // 채팅 메시지를 표시하는 뷰 생성 및 설정
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        if (row == null) {
            // 새로운 행 뷰를 생성
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.chatting_message, parent, false);
        }

        singleMessageContainer = (LinearLayout) row.findViewById(R.id.singleMessageContainer);
        ChatMessage chatMessageObj = getItem(position);
        chatText = (TextView) row.findViewById(R.id.singleMessage);
        chatText.setText(chatMessageObj.message);

        //left -> true: GPT, false: user
        // 채팅 메시지의 왼쪽 또는 오른쪽에 배경 이미지 설정
        chatText.setBackgroundResource(chatMessageObj.left ? R.drawable.bubble_a : R.drawable.bubble_b);

        // 채팅 메시지의 텍스트 정렬을 왼쪽 또는 오른쪽으로 설정
        singleMessageContainer.setGravity(chatMessageObj.left ? Gravity.LEFT : Gravity.RIGHT);
        return row;

    }

    // 바이트 배열을 비트맵으로 디코딩하는 메서드
    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
