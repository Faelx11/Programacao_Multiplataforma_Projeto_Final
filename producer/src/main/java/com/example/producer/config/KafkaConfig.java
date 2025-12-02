package com.example.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configurações de Kafka: cria o tópico com 5 partições e fator de replicação 3.
 */
@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic topic() {
        // O tópico é criado se não existir, com 5 partições e replicação em 3 brokers
        return new NewTopic("mensagens", 5, (short) 3);
    }
}