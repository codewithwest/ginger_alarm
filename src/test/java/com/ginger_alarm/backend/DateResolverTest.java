package com.ginger_alarm.backend;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DateResolverTest {
    private final DateResolver calDateTimeToDate = new DateResolver();
    @Test
    public void ResolveMonth() {
        assertEquals("09", calDateTimeToDate.ResolveDate("Sat Mar 09 21:23:22 SAST 2024"));
    }

    @Test
    public void ResolveTime() {
        assertEquals("03", calDateTimeToDate.ResolveMonth("Sat Mar 09 21:23:22 SAST 2024"));
    }
    @Test
    public void ResolveYear() {
        assertEquals("2024", calDateTimeToDate.ResolveYear("Sat Mar 09 21:23:22 SAST 2024"));
    }
    @Test
    public void ActualAlarmDate() {
        assertEquals("09-03-2024", calDateTimeToDate.ActualAlarmDate("Sat Mar 09 21:23:22 SAST 2024"));
    }
}