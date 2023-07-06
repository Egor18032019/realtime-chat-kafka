package com.kafka.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("msg")
public class MsgController {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public MsgController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //    Контроллер мапится на localhost:8080/msg, в теле запроса передаётся ключ и само сообщений.
    @PostMapping
    public void sendOrder(String msgId, String msg) {
        System.out.println(msgId + " " + msg);
//        kafkaTemplate.send("msg", msgId, msg);

        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("msg", msgId, msg);

        kafkaTemplate.flush();
    }

}
