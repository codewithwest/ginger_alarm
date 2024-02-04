package com.example.ginger_alarm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

final   class FileDirManager  {
     static String username = System.getProperty("user.name");
     static String appFolderPath ="/home/"+username+"/ginger/ginger_alarm/";

    public static void createFileDir(String dir) {
        try {
            if (!Files.isDirectory(Path.of(appFolderPath + dir))) {
                Files.createDirectories(Paths.get(appFolderPath + dir));
            }
        }catch (IOException e){
             System.out.println(e);
        }
    }

    public static List<String> currentDateTime(){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime curDateAndTime = LocalDateTime.now();
        return new ArrayList<>(List.of(dtf.format(curDateAndTime)));
        }

    public static boolean fileExists(String filePath) throws IOException {
        boolean result = true;
        File file = new File(filePath);
        if (!file.exists()) {
            Files.createFile(Path.of(filePath));
        }
        return result;
    }

    public static void writeToFile(String filePath, String logHead, String message) throws IOException {
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
    public static void writeToAlarmsFile(String message) {
        String debugLogFile = appFolderPath + "logs/all_alarms.log";
        String debugHead = currentDateTime() + " Data: ";
        try {
            createFileDir("logs");
            writeToFile(debugLogFile, debugHead, message);
        } catch (IOException e) {
            writeToLogFileError(String.valueOf(e));
        }
    }
    public static void writeToLogFileError(String message) {
        String errorLogFile = appFolderPath+ "logs/ginger_alarm_error.log";
        String errorHead = currentDateTime() + " Error: ";
        try {
            writeToFile(errorLogFile, errorHead, message);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
