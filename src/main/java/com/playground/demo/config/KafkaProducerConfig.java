package com.playground.demo.config;

import com.playground.demo.model.Person;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaTemplate<Void, Person> outPersonKafkaTemplate() {
        KafkaTemplate<Void, Person> outPersonKafkaTemplate = new KafkaTemplate<>(outPersonProducerFactory());
        outPersonKafkaTemplate.setDefaultTopic("out");
        return outPersonKafkaTemplate;
    }

    @Bean
    public ProducerFactory<Void, Person> outPersonProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.VoidSerializer");
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.springframework.kafka.support.serializer.JsonSerializer");
        configProps.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "tx-");
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTransactionManager<Void, Person> kafkaTransactionManager() {
        return new KafkaTransactionManager<>(outPersonProducerFactory());
    }
}
