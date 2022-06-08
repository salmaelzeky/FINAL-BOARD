package com.boards.commands;

import com.boards.configuration.MongoDB;
import com.boards.constants.CollectionNames;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import org.bson.Document;

import org.bson.conversions.Bson;

public class BoardSearch implements Command {

    String board_name;

    public BoardSearch(String boardName) {
        this.board_name = boardName;

    }

    @Override
    public String execute() {

        MongoDB db = new MongoDB();
        MongoCollection boardCollection = db.dbInit(CollectionNames.BOARD.get());
        Bson filter = Filters.eq("name", this.board_name);
     
        MongoCursor <Document> res = boardCollection.find(filter).iterator();
        String result = "";
        while(res.hasNext()){
            result += res.next().toString();
        }
        return result;

    }

}
