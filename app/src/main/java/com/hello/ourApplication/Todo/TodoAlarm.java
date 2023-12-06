package com.hello.ourApplication.Todo;

import java.util.Calendar;

public class TodoAlarm {
    private int id;
    private int year, month, day;
    private int hour;
    private int minute;
    private String name;
    private boolean isAlarmOn;
    private boolean isSwitchVisible;

    public TodoAlarm(int id, int year, int month, int day, int hour, int minute, String name) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.name = name;
        this.isSwitchVisible = true; // 스위치 버튼을 기본적으로 보이게 설정
    }

    public int getId() {
        return id;
    }

    public int getYear() { return year; }

    public int getMonth() { return month; }

    public int getDay() { return day; }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getName() {
        return name;
    }

    public long getTimeInMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
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
