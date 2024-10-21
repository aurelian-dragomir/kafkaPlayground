package com.playground.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;

@Configuration
@Slf4j
public class KafkaErrorConfig {

    //    @Bean
    public KafkaListenerErrorHandler kafkaListenerErrorHandler() {
        return (message, e) -> {
            log.error("Failed to process message {}", message, e);
            throw e;
        };
    }
}
