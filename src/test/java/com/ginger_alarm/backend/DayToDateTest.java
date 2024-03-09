package com.ginger_alarm.backend;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DayToDateTest {

    private final com.ginger_alarm.backend.DayToDate dayToDate = new DayToDate();
    @Test
    public void GetCurrentDay() {
        assertEquals("friday", dayToDate.GetCurrentDay());
    }
    @Test
    public void GetCurrentDate() {
//        assertEquals("08-03-2024", dayToDate.GetCurrentDate());
    }
    @Test
    public void TimeCompare(){
        assertEquals(1, dayToDate.TimeCompare("22", "11"));
    }

    @Test
    public void ResolveDay() {
        assertEquals(5, dayToDate.ResolveDay("friday"));
    }

    @Test
    public void GetNewAlarmDay() {
        assertEquals("15-03-2024", dayToDate.GetNewAlarmDay("friday", "22","11"));
        assertEquals("09-03-2024", dayToDate.GetNewAlarmDay("saturday", "22","00"));
        assertEquals("10-03-2024", dayToDate.GetNewAlarmDay("sunday", "22","11"));
        assertEquals("11-03-2024", dayToDate.GetNewAlarmDay("monday", "22","11"));
        assertEquals("12-03-2024", dayToDate.GetNewAlarmDay("tuesday", "22","11"));
        assertEquals("13-03-2024", dayToDate.GetNewAlarmDay("wednesday", "22","11"));
        assertEquals("14-03-2024", dayToDate.GetNewAlarmDay("thursday", "22","11"));
    }

    @Test
    public void GetAlarmDates() {
        List<String> sampleDays = new ArrayList<>();
        sampleDays.add("thursday");
        sampleDays.add("friday");
        sampleDays.add("saturday");
        List<String> expectedDates = new ArrayList<>();
        expectedDates.add("14-03-2024");
        expectedDates.add("15-03-2024");
        expectedDates.add("16-03-2024");
        assertEquals(expectedDates, dayToDate.GetAlarmDates(sampleDays, "23","23"));

    }

}