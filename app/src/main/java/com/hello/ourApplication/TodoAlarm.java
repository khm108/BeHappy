package com.hello.ourApplication;

import java.util.Calendar;

public class TodoAlarm {
    private int id;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String message;
    private String name;
    private boolean isAlarmOn;
    private boolean isSwitchVisible;

    public TodoAlarm(int id, int hour, int minute, String name) {
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.name = name;
        this.isSwitchVisible = true; // 스위치 버튼을 기본적으로 보이게 설정
    }

    public int getId() {
        return id;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getMessage() {
        return message;
    }
    public String getName() {
        return name;
    }

    public long getTimeInMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    public boolean isSwitchVisible() {
        return isSwitchVisible;
    }

    public void setSwitchVisible(boolean switchVisible) {
        isSwitchVisible = switchVisible;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d - %s", hour, minute, name);
    }

    public void setAlarmOn(boolean alarmOn) {
        isAlarmOn = alarmOn;
    }

    public void setAlarmOff() {
        isAlarmOn = false;
    }

    public boolean isAlarmOn() {
        return isAlarmOn;
    }

}
