package com.ginger_alarm.backend;

import com.ginger_alarm.backend.helpers.DayToDateInterface;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DayToDate implements DayToDateInterface {
    @Override
    public int ResolveDay(String day) {
        return switch (day.toLowerCase()) {
            case "monday" -> 1;
            case "tuesday" -> 2;
            case "wednesday" -> 3;
            case "thursday" -> 4;
            case "friday" -> 5;
            case "saturday" -> 6;
            default -> 7;
        };
    }
    @Override
    public String GetCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
    }

    public LocalDateTime GetCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        return LocalDateTime.now();
    }

    @Override
    public int TimeCompare(String alarmHour, String alarmMins) {
        LocalTime currTime = LocalTime.from(GetCurrentTime());
        LocalTime alarmTime = LocalTime.of(Integer.parseInt(alarmHour), Integer.parseInt( alarmMins));
        // use compareTo() method to compare time1 and time2 objects
        return currTime.compareTo(alarmTime);
    }

    @Override
    public String GetNewAlarmDay(String Day, String hours, String mins) {
        Calendar cal = Calendar.getInstance();
        // creating a constructor of the SimpleDateFormat class
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        // getting current date
        // If the time is greater than the current time and date is today
        if (ResolveDay(Day) != ResolveDay(GetCurrentDay())) {
            cal.add(Calendar.DATE, ResolveDay(Day)+1);
        }else if (TimeCompare(hours, mins) > 0) {
            cal.add(Calendar.DATE, ResolveDay(Day)+1);
        }

        DateResolver dateResolver = new DateResolver();
        return dateResolver.ActualAlarmDate(cal.getTime().toString());

    }

    @Override
    public List<String> GetAlarmDates(List<String> days, String hour, String min) {
        List<String> CleanDays =new ArrayList<>();
        for (String day:days){
            CleanDays.add(String.valueOf(GetNewAlarmDay(day, hour, min)));
        }
        return CleanDays;
    }


}
