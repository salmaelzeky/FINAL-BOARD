package com.boards.commands;

import com.boards.configuration.MongoDB;
import com.boards.constants.CollectionNames;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class DeleteTask implements Command {

    ObjectId task_ID;

    public DeleteTask(String taskID) {
        this.task_ID = new ObjectId(taskID);
    }

    public DeleteResult execute() {
        MongoDB db = new MongoDB();
        MongoCollection sectionCollection = db.dbInit(CollectionNames.SECTION.get());
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());

        Bson query = Filters.eq("_id", this.task_ID);
        DeleteResult result = taskCollection.deleteOne(query);

        return result;
    }

}
