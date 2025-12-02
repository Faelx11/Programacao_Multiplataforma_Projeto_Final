package com.example.producer.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador que expõe endpoint para envio de mensagens ao tópico Kafka.
 */
@RestController
public class MessageController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public MessageController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Envia uma mensagem para o tópico "mensagens".
     *
     * Use por exemplo: /send?message=Olá
     *
     * @param message mensagem a enviar
     * @return confirmação
     */
    @GetMapping("/send")
    public String send(@RequestParam String message) {
        kafkaTemplate.send("mensagens", message);
        return "Mensagem enviada: " + message;
    }
}