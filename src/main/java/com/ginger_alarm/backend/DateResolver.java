package com.ginger_alarm.backend;

import com.ginger_alarm.backend.helpers.DateResolverInterface;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateResolver implements DateResolverInterface {

    /**
     * @param date gets date from date string
     * @return returns the resuts of regex match
     */
    @Override
    public String ResolveDate(@NotNull String date) {
        Pattern pattern = Pattern.compile(" \\d{2} ", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(date.toLowerCase());
        if (matcher.find()){
            return matcher.group().strip();
        }else {
            return "00:00";
        }
    }

    /**
     * @param date resolves month from date string
     * @return and returns month as digit
     */
    @Override
    public  String ResolveMonth(String date) {
        String day;
        date = date.toLowerCase();
        if (date.contains("jan")) {
            day =  "1";
        } else if (date.contains("feb")) {
            day =  "2";
        }else if (date.contains("mar")) {
            day =  "3";
        }else if (date.contains("apr")) {
            day =  "4";
        }else if (date.contains("may")) {
            day =  "5";
        }else if (date.contains("jun")) {
            day =  "6";
        }else if (date.contains("jul")) {
            day =  "7";
        }else if (date.contains("aug")) {
            day =  "8";
        }else if (date.contains("sep")) {
            day =  "9";
        }else if (date.contains("oct")) {
            day =  "10";
        }else if (date.contains("nov")) {
            day =  "11";
        }else {
            day =  "12";
        }

        if (Integer.parseInt(day) < 10){
            return "0"+day;
        }
        return day;
    }

    /**
     * @param date resolves year from year instance
     * @return return the year as digit
     */
    @Override
    public String ResolveYear(@NotNull String date) {
        Pattern pattern = Pattern.compile(" \\d{4}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(date.toLowerCase());
        if (matcher.find()){
            return matcher.group().strip();
        } else {
            return "1974";
        }
    }

    /**
     * @param date uses date instance
     * @return dd-MM-Yyyy format
     */
    @Override
    public String ActualAlarmDate(String date) {
        return this.ResolveDate(date)+ "-"+this.ResolveMonth(date)+"-"+this.ResolveYear(date);
    }
}
