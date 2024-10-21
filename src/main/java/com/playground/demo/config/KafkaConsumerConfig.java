package com.playground.demo.config;

import com.playground.demo.handler.KafkaContainerErrorHandler;
import com.playground.demo.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ConcurrentKafkaListenerContainerFactory<Void, Person> inContainerFactory(
            ConsumerFactory<Void, Person> consumerFactory, KafkaContainerErrorHandler errorHandler) {
        ConcurrentKafkaListenerContainerFactory<Void, Person> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(consumerFactory);
        containerFactory.setCommonErrorHandler(errorHandler);
        return containerFactory;
    }
}