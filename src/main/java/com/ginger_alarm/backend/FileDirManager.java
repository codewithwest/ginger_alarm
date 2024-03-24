package com.ginger_alarm.backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public  class FileDirManager  {
      String username = System.getProperty("user.name");
      String appFolderPath ="/home/"+username+"/.ginger/ginger_alarm/";
    DateResolver dateResolver = new DateResolver();
    DayToDate dayToTime = new DayToDate();

    public  void createFileDir(String dir) {
        try {
            if (!Files.isDirectory(Path.of(appFolderPath + dir))) {
                Files.createDirectories(Paths.get(appFolderPath + dir));
            }
        }catch (IOException e){
             writeToLogFileError(e.toString());
        }
    }

    public static List<String> currentDateTime(){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime curDateAndTime = LocalDateTime.now();
        return new ArrayList<>(List.of(dtf.format(curDateAndTime)));
        }

    public  boolean fileExists(String filePath) throws IOException {
        boolean result = true;
        File file = new File(filePath);
        if (!file.exists()) {
            Files.createFile(Path.of(filePath));
        }
        return result;
    }

    public  void writeToFile(String filePath, String logHead, String message) throws IOException {
        if (fileExists(filePath)) {
            FileWriter myWriter = new FileWriter(filePath, true);
            myWriter.write(logHead + message + "\n");
            myWriter.close();
        }
    }
    public  void writeToLogFileDebug(String message) {
        String debugLogFile = appFolderPath + "logs/ginger_alarm_debug.log";
        String debugHead = currentDateTime() + " DEBUG: ";
        try {
            createFileDir("logs");
            writeToFile(debugLogFile, debugHead, message);
        } catch (IOException e) {
            writeToLogFileError(String.valueOf(e));
        }
    }
    public  void writeToAlarmsFile(String message) {
        String debugLogFile = appFolderPath + "logs/all_alarms.log";
        String debugHead = currentDateTime() + " Alarm Created: ";
        try {
            createFileDir("logs");
            writeToFile(debugLogFile, debugHead, message);
        } catch (IOException e) {
            writeToLogFileError(String.valueOf(e));
        }
    }
    public  void writeToLogFileError(String message) {
        String errorLogFile = appFolderPath+ "logs/ginger_alarm_error.log";
        String errorHead = currentDateTime() + " Error: ";
        try {
            createFileDir("logs");
            writeToFile(errorLogFile, errorHead, message);
        } catch (IOException e) {
            writeToLogFileError(e.toString());
        }
    }

    public void writeToAlarmsFileCue(String message) throws IOException {
        String alarmCueLogFile = appFolderPath + ".alarms/alarms_static.log";

        try {
            createFileDir(".alarms");
            if (!checkIfLineInFile(alarmCueLogFile,message)){
                writeToFile(alarmCueLogFile, "", message);
            }

        } catch (IOException e) {
            writeToLogFileError(String.valueOf(e));
        }
    }
    public boolean checkForMatchingAlarmTime(String message) throws Exception {
        String alarmCueLogFile = appFolderPath + ".alarms/alarms_static.log";

        if (checkIfLineInFileMatchesCurrentDateTime(alarmCueLogFile)){
            System.out.println(message);
            writeToLogFileDebug(message);
            return true;

        }

        return false;
    }

    public boolean checkIfLineInFile(String filePath, String stringLine) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(filePath));
            for (String line : allLines) {
                if (line.equals(stringLine)) {
                    return true;
                }
            }
        } catch (IOException e) {
            writeToLogFileError(e.toString());
        }
        return false;
    }

    public boolean checkIfLineInFileMatchesCurrentDateTime(String filePath) {
        String todaysDate = String.valueOf(Calendar.getInstance().getTime());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        String currTime = dtf.format(LocalDateTime.now());
        todaysDate = dateResolver.ActualAlarmDate(todaysDate);
        try {
            List<String> allLines = Files.readAllLines(Paths.get(filePath));
            for (String line : allLines) {
                System.out.println("CurrentLine: " + line);
                System.out.println("Contains Time: " + currTime);
                System.out.println("Contains Date: " + todaysDate);
                if (line.contains(currTime) && line.contains(todaysDate)) {
                    return true;
                }
            }
        } catch (IOException e) {
            writeToLogFileError(e.toString());
        }
        return false;
    }
}
