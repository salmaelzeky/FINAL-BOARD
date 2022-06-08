package com.boards.receiver;

import com.boards.commands.*;
import com.boards.configuration.Helpers;
import com.boards.configuration.RabbitMQConfig;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RecvController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecvController.class);

    @Autowired
    RabbitTemplate rabbitTemplate;


    @RabbitListener(queues = RabbitMQConfig.MESSAGE_QUEUE)
    public void listener(Message message) {
        byte[] body = message.getBody();
        Helpers helpers = new Helpers();
        JSONObject jsonObject = helpers.parseToJson(new String(body));
        switch (message.getMessageProperties().getMessageId()){
            case "create-board": {
                CreateBoard createBoard = new CreateBoard((String) jsonObject.get("name"), (String) jsonObject.get("user_id"));
                InsertOneResult result = createBoard.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody(("Inserted a document with the following id: "
                        + result.getInsertedId().asObjectId().getValue()).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.REPLY_QUEUE, build, correlationData);
                break;
            }
            case "create-section": {
                CreateSection createSection = new CreateSection((String) jsonObject.get("name"), (String) jsonObject.get("user_id"), (String) jsonObject.get("board_id"));
                InsertOneResult result = createSection.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody(("Inserted a document with the following id: "
                        + result.getInsertedId().asObjectId().getValue()).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.REPLY_QUEUE, build, correlationData);
                break;
            }
            case "create-task": {
                CreateTaskInSection createTaskInSection = new CreateTaskInSection((String) jsonObject.get("name"), (String) jsonObject.get("section_id"));
                InsertOneResult result = createTaskInSection.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody(("Inserted a document with the following id: "
                        + result.getInsertedId().asObjectId().getValue()).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.REPLY_QUEUE, build, correlationData);
                break;
            }
            case "create-subtask": {
                CreateSubtaskInSection createSubtask = new CreateSubtaskInSection((String) jsonObject.get("name"), (String) jsonObject.get("task_id"));
                InsertOneResult result = createSubtask.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody(("Inserted a document with the following id: "
                        + result.getInsertedId().asObjectId().getValue()).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.REPLY_QUEUE, build, correlationData);
                break;
            }

            case "assign-task": {
                AssignTask assignToTask = new AssignTask((String) jsonObject.get("task_id"), (String) jsonObject.get("user_id"));
                String result = assignToTask.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody((result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.REPLY_QUEUE, build, correlationData);
                break;
            }


            case "edit-task": {
                EditTask editTask= new EditTask((String) jsonObject.get("task_id"), (String) jsonObject.get("task_name"),(String) jsonObject.get("piority"),(String) jsonObject.get("due_date"),(Boolean) jsonObject.get("done"));
                String result = editTask.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody((result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.REPLY_QUEUE, build, correlationData);
                break;
            }


            case "edit-section": {
                EditSection editSection= new EditSection((String) jsonObject.get("section_id"), (String) jsonObject.get("section_name"));
                String result = editSection.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody((result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.REPLY_QUEUE, build, correlationData);
                break;
            }


            case "search-board": {
                BoardSearch searchBoard= new BoardSearch( (String) jsonObject.get("board_name"));
                String result = searchBoard.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody((result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.REPLY_QUEUE, build, correlationData);
                break;
            }


            case "search-task": {
                TaskSearch searchTask= new TaskSearch( (String) jsonObject.get("task_name"));
                String result = searchTask.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody((result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.REPLY_QUEUE, build, correlationData);
                break;
            }


            case "sort-task": {
                SortTasks sortTask = new SortTasks( (String) jsonObject.get("section_id"),(String) jsonObject.get("sort_by"),(String) jsonObject.get("order"));
                String result = sortTask.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody((result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.REPLY_QUEUE, build, correlationData);
                break;
            }


            case "sort-section": {
                SortSections sortSection = new SortSections( (String) jsonObject.get("section_id"),(String) jsonObject.get("sort_by"),(String) jsonObject.get("order"));
                String result = sortSection.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody((result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.REPLY_QUEUE, build, correlationData);
                break;
            }

            case "task-deadline": {
                DeadlineTask deadlineTask = new DeadlineTask( );
                String result = deadlineTask.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody((result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.REPLY_QUEUE, build, correlationData);
                break;
            }


            case "delete-task-board": {
                DeleteTask deleteTask = new DeleteTask((String) jsonObject.get("task_id"));
                DeleteResult result = deleteTask.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody(("Task was deleted successfully").getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.REPLY_QUEUE, build, correlationData);
                break;
            }



            case "delete-section": {
                DeleteSection deleteSection = new DeleteSection((String) jsonObject.get("board_id"), (String) jsonObject.get("section_id"));
                DeleteResult result = deleteSection.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody(("Task was deleted successfully").getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.REPLY_QUEUE, build, correlationData);
                break;
            }


            case "add-collaborator": {
                AddCollaborator addCollaborator = new AddCollaborator((String) jsonObject.get("user_id"), (String) jsonObject.get("board_id"));
                String result = addCollaborator.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody((result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.REPLY_QUEUE, build, correlationData);
                break;
            }

            case "add-comment-board": {
                AddCommentBoard addCommentBoard = new AddCommentBoard((String) jsonObject.get("comment"), (String) jsonObject.get("board_id"));
                String result = addCommentBoard.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody((result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.REPLY_QUEUE, build, correlationData);
                break;
            }

            case "add-comment-section": {
                AddCommentTaskSection addCommentSection = new AddCommentTaskSection((String) jsonObject.get("comment"), (String) jsonObject.get("task_id"));
                String result = addCommentSection.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody((result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.REPLY_QUEUE, build, correlationData);
                break;
            }


            default:
                break;
        }
    }

}