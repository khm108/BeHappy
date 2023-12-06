package com.hello.ourApplication.Todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.hello.ourApplication.R;

import java.util.ArrayList;

public class TodoAlarmAdapter extends ArrayAdapter<TodoAlarm> {

    public TodoAlarmAdapter(Context context, ArrayList<TodoAlarm> alarms) {
        super(context, 0, alarms);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoAlarm alarm = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_custom_list_item, parent, false);
        }

        TextView alarmText = convertView.findViewById(R.id.alarmText);
        TextView alarmName = convertView.findViewById(R.id.alarmName);
        Switch switchBtn = convertView.findViewById(R.id.switchBtn);

        int year = alarm.getYear();
        int month = alarm.getMonth();
        int day = alarm.getDay();
        int hour = alarm.getHour();
        int minute = alarm.getMinute();
        String name = alarm.getName();

        String nameText = String.format("%s", name);
        alarmName.setText("안녕");

        String timeText = String.format("%04d년 %02d월 %02d일\n%02d시 %02d분", year, month+1, day, hour, minute);
        alarmText.setText(timeText);

        // 스위치 상태 설정
        switchBtn.setChecked(alarm.isAlarmOn());

        // 스위치 표시 여부 설정
        switchBtn.setVisibility(alarm.isSwitchVisible() ? View.VISIBLE : View.GONE);

        switchBtn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // 스위치가 켜질 때 알람을 활성화
                alarm.setAlarmOn(true);
                // 알람 활성화에 관한 추가 작업 수행
            } else {
                // 스위치가 꺼질 때 알람을 비활성화
                alarm.setAlarmOff();
                // 알람 비활성화에 관한 추가 작업 수행
            }
        });

        return convertView;
    }
}