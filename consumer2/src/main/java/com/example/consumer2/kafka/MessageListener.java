package com.example.consumer2.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    @KafkaListener(topics = "mensagens", groupId = "consumer2-group")
    public void listen(String message) {
        System.out.println("Consumidor 2 recebeu: " + message);
    }
}