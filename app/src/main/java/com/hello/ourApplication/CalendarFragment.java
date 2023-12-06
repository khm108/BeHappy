package com.hello.ourApplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class CalendarFragment extends Fragment {

    private Calendar calendar;
    private CalendarView calendarView;
    private HashMap<Long, String> emojiData = new HashMap<>(); // 날짜와 이모티콘 데이터 매핑

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.calendar, container, false);

        calendarView = rootView.findViewById(R.id.calendarView);

        // 날짜를 선택할 때 이벤트 리스너 등록
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // 선택한 날짜의 이모티콘을 가져옴
            String emoji = emojiData.get(getDateInMillis(year, month, dayOfMonth));

            if (emoji != null) {
                // 이모티콘이 있는 경우 토스트로 표시 또는 원하는 처리 수행
                Toast.makeText(requireContext(), "Selected Emoji: " + emoji, Toast.LENGTH_SHORT).show();
            } else {
                // 이모티콘이 없는 경우 선택 이벤트 처리
                // 여기에서 원하는 동작 수행
            }
        });

        // TODO: 달력을 구현하는 코드를 추가하세요.
        return rootView;
    }

    // 날짜에 이모티콘 추가하는 메서드
    public void addEmojiToCalendar(int year, int month, int dayOfMonth, String emoji) {
        long dateInMillis = getDateInMillis(year, month, dayOfMonth);
        emojiData.put(dateInMillis, emoji);
        updateCalendar(); // 달력 갱신
    }

    // 달력 갱신 메서드
    public void updateCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(calendarView.getDate());

        // 달력의 각 날짜에 이모티콘 표시
        for (int day = 1; day <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); day++) {
            calendar.set(Calendar.DAY_OF_MONTH, day);

            long dateInMillis = calendar.getTimeInMillis();
            String emoji = emojiData.get(dateInMillis);

            if (emoji != null) {
                // 해당 날짜에 이모티콘이 있는 경우
                // TODO: 날짜에 이모티콘을 표시하는 작업 수행
            } else {
                // 해당 날짜에 이모티콘이 없는 경우
                // TODO: 날짜에 이모티콘을 지우는 작업 수행 (필요한 경우)
            }
        }
    }

    // 날짜에 해당하는 요일의 인덱스를 가져오는 메서드
    private int getDayIndex(int dayOfWeek) {
        // 일요일(1)부터 토요일(7)까지의 인덱스 반환 (0부터 시작)
        return (dayOfWeek + 5) % 7;
    }

    // 해당 날짜에 대한 TextView를 찾는 메서드
    private TextView findDayTextView(int dayIndex) {
        // TODO: 달력의 각 날짜에 대한 TextView를 찾는 코드 추가
        // 예를 들어, 달력의 각 날짜에 대한 ID를 정의하고 findViewById로 찾는 방법 등을 사용
        // 일반적으로는 RecyclerView 또는 GridView 등을 사용하여 날짜를 표시하므로 해당 방식에 맞게 구현
        return null; // 예제 코드이므로 구체적인 레이아웃 구조에 맞게 수정이 필요
    }

    // 날짜를 밀리초로 변환하는 유틸리티 메서드
    private long getDateInMillis(int year, int month, int dayOfMonth) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", java.util.Locale.getDefault());
        Date date = new Date(year - 1900, month, dayOfMonth);
        return Long.parseLong(sdf.format(date));
    }
}