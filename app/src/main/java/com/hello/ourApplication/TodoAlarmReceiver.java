package com.hello.ourApplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class TodoAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int alarmId = intent.getIntExtra("alarmId", -1);

        if (alarmId != -1) {
            // 알람이 울릴 때 수행할 작업을 여기에 추가
            // 이 예시에서는 간단히 토스트 메시지를 표시
            Toast.makeText(context, "알람 #" + alarmId + "이 울립니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
