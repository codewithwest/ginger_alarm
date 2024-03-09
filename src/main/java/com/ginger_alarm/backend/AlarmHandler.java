package com.ginger_alarm.backend;

import com.ginger_alarm.backend.helpers.AlarmHandlerInterface;

public class AlarmHandler implements AlarmHandlerInterface {


    // Todo
    @Override
    public boolean AlarmIsToday() {
        return false;
    }

    @Override
    public boolean AlarmIsRepeated() {
        return false;
    }

    @Override
    public boolean DeleteExecutedAlarm() {
        return false;
    }

    @Override
    public String ResolveFutureAlarmDateAlarmRepeated() {
        return null;
    }
}
