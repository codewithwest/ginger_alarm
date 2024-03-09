package com.ginger_alarm.backend.helpers;

public interface DateResolverInterface {

    String ResolveDate(String date);

    String ResolveMonth(String date);

    String ResolveYear(String date);

    String ActualAlarmDate(String date);
}
