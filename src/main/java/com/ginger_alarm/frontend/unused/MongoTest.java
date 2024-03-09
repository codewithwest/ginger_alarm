package com.ginger_alarm.frontend.unused;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;


public class MongoTest {



        public static void main( String args[] ) {
            String uri = "mongodb://localhost:27017";


            MongoClient connection = MongoClients.create(uri);
            MongoDatabase database = connection.getDatabase("ginger_test");
            MongoCollection<Document> DBCollection = database.getCollection("alarm");
            Document document = DBCollection.find(Filters.eq("message", "west")).first();
            assert document != null;
            System.out.println(document);
//            FindIterable<Document> documents = collection.find();
//            for (Document document : documents) {
//                System.out.println(document);
//            }
        }

}
