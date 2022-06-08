package com.boards.commands;

import com.boards.configuration.MongoDB;
import com.boards.constants.CollectionNames;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.FindIterable;

import org.bson.Document;
import org.bson.types.ObjectId;

public class SortTasks implements Command {
    String sort;
    String order;
    String sectionID;

    public SortTasks(String sectionID, String sort, String order) {
        this.sort = sort;
        this.sectionID = sectionID;

        this.order = order;

    }

    @Override
    public String execute() {

        MongoDB db = new MongoDB();
        MongoCollection<Document> SectionCollection = db.dbInit(CollectionNames.SECTION.get());
        MongoCollection TaskCollection = db.dbInit(CollectionNames.TASK.get());
        FindIterable sortedTasks;

        if (order.equals("asec")) {
            sortedTasks = TaskCollection.find().sort(Sorts.ascending(sort));

        } else {
            sortedTasks = TaskCollection.find().sort(Sorts.descending(sort));
        }
 
        Document result = (Document) sortedTasks;
        
        return result.toJson();

    }
}
