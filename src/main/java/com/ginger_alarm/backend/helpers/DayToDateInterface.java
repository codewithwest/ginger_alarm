package com.ginger_alarm.backend.helpers;

import java.util.List;

public interface DayToDateInterface {


    int ResolveDay(String day);
    

    String GetCurrentDay();


    int TimeCompare(String alarmHour, String alarmMins);

    String GetNewAlarmDay(String Day, String hours, String mins);


    List<String> GetAlarmDates(List<String> days, String hour, String min);
}
