package com.playground.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaAdminConfig {

    @Value("${spring.kafka.template.default-topic}")
    private String outTopic;

    @Bean
    public KafkaAdmin.NewTopics createTopicsBean() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("in")
                        .build(),
                TopicBuilder.name(outTopic)
                        .build()
        );
    }
}
