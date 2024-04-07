package com.ginger_alarm.backend.helpers;

/**
 * cLass will handler all related to cleaning up the alarm trash
 */
public interface AlarmHandlerInterface {
    boolean AlarmIsToday();
    boolean AlarmIsRepeated();
    boolean DeleteExecutedAlarm();
    String ResolveFutureAlarmDateAlarmRepeated();

}
