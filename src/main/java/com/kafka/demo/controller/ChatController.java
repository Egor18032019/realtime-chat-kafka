package com.kafka.demo.controller;

import com.kafka.demo.model.Message;
import com.kafka.demo.utils.KafkaConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
@RestController
public class ChatController {
    //TODO вынести все адреса в утилиты
    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @PostMapping(value = "/api/send", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody Message message) {
        message.setTimestamp(LocalDateTime.now().toString());
        try {
            //Отправка сообщения
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        //TODO CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("msg", msgId, msg);
        //
        //        kafkaTemplate.flush();
        //TODO отдавать ответ в виде ResponseBody

    }

    //    -------------- WebSocket API ----------------
    @MessageMapping("/sendMessage")
    @SendTo("/topic/group")
    public Message broadcastGroupMessage(@Payload Message message) {
        //Sending this message to all the subscribers
        return message;
    }

    @MessageMapping("/newUser")
    @SendTo("/topic/group")
    public Message addUser(@Payload Message message,
                           SimpMessageHeaderAccessor headerAccessor) {
        // Add user in web socket session
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return message;
    }

}


