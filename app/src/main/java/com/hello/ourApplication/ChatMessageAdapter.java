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
//    List msgs = new ArrayList<ChatMessage>();
    private TextView chatText;
    private List chatMessageList = new ArrayList<ChatMessage>();
    private LinearLayout singleMessageContainer;

    public void add(@Nullable ChatMessage object) {
        chatMessageList.add(object);
        super.add(object);
    }

    public ChatMessageAdapter(@NonNull Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public int getCount() {
        return this.chatMessageList.size();
    }

    @Nullable
    @Override
    public ChatMessage getItem(int index) {
        return (ChatMessage) this.chatMessageList.get(index);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View row = convertView;
//        if(row==null){
//            // inflator를 생성하여, chatting_message.xml을 읽어서 View객체로 생성한다.
//            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            row = inflater.inflate(R.layout.chatting_message, parent, false);
//        }
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.chatting_message, parent, false);
        }

        singleMessageContainer = (LinearLayout) row.findViewById(R.id.singleMessageContainer);
        ChatMessage chatMessageObj = getItem(position);
        chatText = (TextView) row.findViewById(R.id.singleMessage);
        chatText.setText(chatMessageObj.message);
        chatText.setBackgroundResource(chatMessageObj.left ? R.drawable.bubble_a : R.drawable.bubble_b);
        singleMessageContainer.setGravity(chatMessageObj.left ? Gravity.LEFT : Gravity.RIGHT);
        return row;

//        // Array List에 들어 있는 채팅 문자열을 읽음.
//        ChatMessage msg = (ChatMessage) msgs.get(position);
//
//        // Inflater를 이용해서 생성한 View에, ChatMessage 삽입
//        TextView msgText = (TextView) row.findViewById(R.id.chatmessage);
//        msgText.setText(msg.getMessage());
//        msgText.setTextColor(Color.parseColor("#000000"));
//
//        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
