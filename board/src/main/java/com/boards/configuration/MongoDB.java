package com.boards.configuration;
import com.boards.constants.CollectionNames;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDB {

    public MongoDB(){

    }

    public MongoCollection dbInit(String collectionName){
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("Board");
        MongoCollection collection = database.getCollection(collectionName);
        return collection;
    }

    public static void main(String[] args) {
        MongoDB db = new MongoDB();
        MongoCollection collection = db.dbInit(CollectionNames.BOARD.get());
        Document document = new Document("name", "board1");
        collection.insertOne(document);

    }


}
