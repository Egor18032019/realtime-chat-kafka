package com.kafka.demo;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MsgListener {
    @KafkaListener(topics="msg")
    public void msgListener(String msg){
        System.out.println("! msg " + msg);
    }
}
