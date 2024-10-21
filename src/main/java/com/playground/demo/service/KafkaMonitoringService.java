package com.playground.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaMonitoringService {
    private final KafkaAdmin kafkaAdmin;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Scheduled(fixedDelayString = "5", timeUnit = TimeUnit.SECONDS)
    public void run() {
        try {
            kafkaAdmin.describeTopics(topic);
            log.info("kafka cluster is up");
        } catch (Exception e) {
            log.error("kafka cluster is down", e);
        }
    }
}
