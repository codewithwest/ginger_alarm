package com.ginger_alarm.backend.helpers;

public interface AlarmHandlerInterface {
    boolean AlarmIsToday();
    boolean AlarmIsRepeated();
    boolean DeleteExecutedAlarm();
    String ResolveFutureAlarmDateAlarmRepeated();


}
