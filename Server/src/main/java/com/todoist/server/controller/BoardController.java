package com.todoist.server.controller;

import com.todoist.server.config.List.*;
import org.json.simple.parser.ParseException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api/todolist/board")
public class BoardController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/createBoard")
    public Map createBoard(@RequestBody String body){
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("create-board");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @PostMapping("/createSection")
    public Map createSection(@RequestBody String body){
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("create-section");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @PostMapping("/createTask")
    public Map createTaskInSection(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("create-task");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @PostMapping("/createSubtask")
    public Map createSubtask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("create-subtask");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @PostMapping("/assignTask")
    public Map assignToTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("assign-task");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @PostMapping("/editTask")
    public Map editTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("edit-task");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @GetMapping("/boardSearch")
    public Map searchBoard(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("search-board");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @GetMapping("/taskSearch")
    public Map searchTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("search-task");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @GetMapping("/taskSort")
    public Map sortTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("sort-task");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @GetMapping("/deadlineTask")
    public Map deadlineTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("task-deadline");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @DeleteMapping("/deleteTask")
    public Map deleteTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("delete-task-board");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @DeleteMapping("/deleteSection")
    public Map deleteSection(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("delete-section");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @PostMapping("/boardComment")
    public Map addCommentBoard(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("add-comment-board");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @PostMapping("/sectionComment")
    public Map addCommentSection(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("add-comment-section");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }


    @PostMapping("/addCollab")
    public Map addCollaborator(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("add-collaborator");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.EXCHANGE, RabbitMQConfig.MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }
}
