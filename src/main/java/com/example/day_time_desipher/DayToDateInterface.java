package com.example.day_time_desipher;

import java.util.Date;
import java.util.List;

public interface DayToDateInterface {


    int ResolveDay(String day);

    String GetCurrentDay();


    int TimeCompare(String alarmHour, String alarmMins);

    Date GetNewAlarmDay(String Day, String hours, String mins);


    List<String> GetAlarmDates(List<String> days, String hour, String min);
}
