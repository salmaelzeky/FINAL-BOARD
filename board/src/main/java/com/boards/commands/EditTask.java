package com.boards.commands;

import com.boards.configuration.MongoDB;
import com.boards.constants.CollectionNames;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Date;

public class EditTask implements Command {

    String taskName;
    String task_ID;
    String priority;
    String due_date;
    Boolean done;

    public EditTask(String taskID, String taskName, String priority, String dueDate, Boolean done) {
        this.taskName = taskName;
        this.task_ID =taskID;
        this.done = done;
        this.due_date = dueDate;
        this.priority = priority;
    }

    @Override
    public String execute() {

        MongoDB db = new MongoDB();
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());

        Bson filter = Filters.eq("task_id", this.task_ID);
        //   UpdateResult res = taskCollection.updateOne(filter);


        if (taskName != null) {
            Bson update1 = Updates.set("name", taskName);
            taskCollection.updateOne(filter, update1);
        }

        if (done != null) {
            Bson update2 = Updates.set("done", done);
            taskCollection.updateOne(filter, update2);
        }
        if (due_date != null) {
            Bson update3 = Updates.set("due_date", due_date);
            taskCollection.updateOne(filter, update3);
        }
        if (priority != null) {
            Bson update4 = Updates.set("priority", priority);
            taskCollection.updateOne(filter, update4);
        }

        // Updates.combine(),

        Bson filter2 = Filters.eq("name", this.taskName);
        // Bson projection = Projections.include("name");
        // System.out.println("Projections: "+filter);
        MongoCursor <Document> res = taskCollection.find(filter2).iterator();
        String result = "";
        while(res.hasNext()){
            result += res.next().toString();
        }
        return result;


    }
}
