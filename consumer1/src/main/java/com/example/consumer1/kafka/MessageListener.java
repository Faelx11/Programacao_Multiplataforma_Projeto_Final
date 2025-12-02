package com.example.consumer1.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Listener Kafka para o primeiro consumidor.
 */
@Component
public class MessageListener {
    @KafkaListener(topics = "mensagens", groupId = "consumer1-group")
    public void listen(String message) {
        System.out.println("Consumidor 1 recebeu: " + message);
    }
}