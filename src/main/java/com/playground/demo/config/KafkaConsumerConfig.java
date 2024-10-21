package com.playground.demo.config;

import com.playground.demo.handler.KafkaContainerErrorHandler;
import com.playground.demo.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    private final KafkaTransactionManager<Void, Person> outPersonKafkaTransactionManager;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Void, Person> inContainerFactory(
            ConsumerFactory<Void, Person> consumerFactory, KafkaContainerErrorHandler errorHandler) {
        ConcurrentKafkaListenerContainerFactory<Void, Person> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(consumerFactory);
        containerFactory.setCommonErrorHandler(errorHandler);
        return containerFactory;
    }
}