package com.boards.commands;

import com.boards.configuration.MongoDB;
import com.boards.constants.CollectionNames;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class AddCommentTaskSection implements Command{

    String content;
    ObjectId task_ID;

    public AddCommentTaskSection(String comment, String taskID) {
        this.content = comment;
        this.task_ID = new ObjectId(taskID);

    }

    public String execute() {
        MongoDB db = new MongoDB();
        MongoCollection taskCollection =  db.dbInit(CollectionNames.TASK.get());
        MongoCollection commentCollection = db.dbInit(CollectionNames.COMMENT.get());


        Document comment = new Document("content", this.content);
        InsertOneResult result = commentCollection.insertOne(comment);


        Bson filter = Filters.eq("task_id", this.task_ID);

        Bson update = Updates.push("comments", result.getInsertedId().asObjectId());
        Document res = (Document)  taskCollection.findOneAndUpdate(filter, update);
        return res.toJson();
    }

}