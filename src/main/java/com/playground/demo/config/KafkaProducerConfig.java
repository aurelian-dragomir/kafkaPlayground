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

    @Value("${spring.kafka.producer.in.key-serializer}")
    private String inKeySerializer;

    @Value("${spring.kafka.producer.in.value-serializer}")
    private String inValueSerializer;

    @Value("${spring.kafka.producer.out.key-serializer}")
    private String outKeySerializer;

    @Value("${spring.kafka.producer.out.value-serializer}")
    private String outValueSerializer;

    @Value("${spring.kafka.producer.out.tx-prefix}")
    private String outTxPrefix;

    @Value("${spring.kafka.producer.out.topic}")
    private String outTopic;

    @Value("${spring.kafka.producer.in.topic}")
    private String inTopic;

    @Bean
    public KafkaTemplate<Void, Person> inPersonKafkaTemplate() {
        KafkaTemplate<Void, Person> inPersonKafkaTemplate = new KafkaTemplate<>(inPersonProducerFactory());
        inPersonKafkaTemplate.setDefaultTopic(inTopic);
        return inPersonKafkaTemplate;
    }

    @Bean
    public ProducerFactory<Void, Person> inPersonProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, inKeySerializer);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, inValueSerializer);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<Void, Person> outPersonKafkaTemplate() {
        KafkaTemplate<Void, Person> outPersonKafkaTemplate = new KafkaTemplate<>(outPersonProducerFactory());
        outPersonKafkaTemplate.setDefaultTopic(outTopic);
        return outPersonKafkaTemplate;
    }

    @Bean
    public ProducerFactory<Void, Person> outPersonProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, outKeySerializer);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, outValueSerializer);
        configProps.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, outTxPrefix);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTransactionManager<Void, Person> outPersonKafkaTransactionManager() {
        return new KafkaTransactionManager<>(outPersonProducerFactory());
    }
}
