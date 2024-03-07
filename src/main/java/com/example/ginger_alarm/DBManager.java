package com.example.ginger_alarm;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class DBManager {
    String uri = "mongodb://localhost:27017";

    public MongoClient DBConnection(){
        try (MongoClient getConn = MongoClients.create(this.uri)){
            return getConn;
        }
    }

    public MongoCollection<Document> GetCollection(){
        MongoClient connection = DBConnection();
            if (connection != null) {
                MongoDatabase database = connection.getDatabase("ginger_test");
                //                Document news= DBCollection.find(Filters.eq("message", "message")).first();
                return database.getCollection("alarm");
            }
            return null;
    }

    public void AlarmUpdate(@NotNull String Option, String AlarmMessage, String AlarmTime, Boolean Repeated, List<String> AlarmList) {

        MongoClient connection = MongoClients.create(uri);
        MongoDatabase database = connection.getDatabase("ginger_test");
        MongoCollection<Document> DBCollection = database.getCollection("alarm");
        Document document = DBCollection.find(Filters.eq("message", AlarmMessage)).first();
        if (Option.equals("new")) {
            if (document == null) {
                InsertOneResult createEntry = DBCollection.insertOne(new Document()
                        .append("message", AlarmMessage)
                        .append("time", AlarmTime)
                        .append("repeat", Repeated)
                        .append("days", AlarmList));
                System.out.println("Data Added");
                System.out.println("Success! Inserted document id: " + createEntry.getInsertedId());
            } else {
                DBCollection.deleteOne(document);
                InsertOneResult updateEntry = DBCollection.insertOne(new Document()
                        .append("message", AlarmMessage)
                        .append("time", AlarmTime)
                        .append("repeat", Repeated)
                        .append("days", AlarmList));
                System.out.println("Data Updated");
                TimeManager CurrTimer = new TimeManager();
                CurrTimer.CurrentTime();
                System.out.println("Success! Inserted document id: " + updateEntry.getInsertedId());
            }
        }else {
            if (document != null){
                System.out.println(document.get("time"));
                CurrentTime();
                if (document.get("time") == "12:50"){
                    System.out.println(document.get("message"));
                }
            }


        }

    }

    public String CurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String dat = dtf.format(now);
        System.out.println(Arrays.asList(dat.split(" ")));

        Calendar cal = Calendar.getInstance();
        //creating a constructor of the SimpleDateFormat class
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        //getting current date
        System.out.println("Today's date: " + sdf.format(cal.getTime()));
        //creating a constructor of the Format class
        //displaying full-day name
        Format f = new SimpleDateFormat("EEEE");
        String str = f.format(new Date());
        //prints day name
        System.out.println("Day Name: " + str);

        // creating a new object of the class Date
        java.util.Date date = new java.util.Date();
        System.out.println(Arrays.asList(date.toString().split(" ")));
        return dtf.toString();
    }
}