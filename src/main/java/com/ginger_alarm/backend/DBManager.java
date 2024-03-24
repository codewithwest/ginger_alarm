package com.ginger_alarm.backend;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class DBManager {
    String uri = "mongodb://localhost:27017";
//    DayToDate dateResolver = new DayToDate();
    DateResolver dateResolver = new DateResolver();


    public MongoClient DBConnection() {
        try (MongoClient getConn = MongoClients.create(this.uri)) {
            return getConn;
        }
    }

    public void GetCollection() {
        System.out.println("No alarms found!!!One");

        // MongoClient connection = DBConnection();
        // if (connection != null) {
        MongoClient connection = MongoClients.create(uri);
        MongoDatabase database = connection.getDatabase("ginger_test");
        MongoCollection<Document> collection = database.getCollection("alarm");
        Document document = collection.find(Filters.eq("message", "test")).first();
        assert document != null;
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                // Create Alarms cue file
                FileDirManager alarmsFileWriter = new FileDirManager();
                Document alarmData = cursor.next();
                String todaysDate = String.valueOf(Calendar.getInstance().getTime());
                todaysDate = dateResolver.ActualAlarmDate(todaysDate);

                if (alarmData.get("days").toString().contains(todaysDate)) {

                    alarmsFileWriter.writeToAlarmsFileCue("time: " + alarmData.get("time") + " date: " +
                            todaysDate);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void AlarmUpdate(@NotNull String Option, String AlarmMessage,
            String AlarmTime, Boolean Repeated,
            List<String> AlarmList) {

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

                TimeManager.CurrentTime();
                System.out.println("Success! Inserted document id: " + updateEntry.getInsertedId());
            }
        } else {
            if (document != null) {
                System.out.println(document.get("time"));
                TimeManager.CurrentTime();
                if (document.get("time") == "12:50") {
                    System.out.println(document.get("message"));
                }
            }

        }

    }



    }