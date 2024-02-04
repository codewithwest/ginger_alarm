package com.example.ginger_alarm;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;


public class MongoDB {
    public static void main( String args[] ) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String dat = dtf.format(now).toString();
        System.out.println(Arrays.asList(dat.split(" ")));

        Calendar cal = Calendar.getInstance();
        //creating a constructor of the SimpleDateFormat class
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        //getting current date
        System.out.println("Today's date: "+sdf.format(cal.getTime()));
        //creating a constructor of the Format class
        //displaying full-day name
        Format f = new SimpleDateFormat("EEEE");
        String str = f.format(new Date());
        //prints day name
        System.out.println("Day Name: "+str);

        // creating a new object of the class Date
        java.util.Date date = new java.util.Date();
        System.out.println(Arrays.asList(date.toString().split(" ")));

    }
}