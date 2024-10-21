package com.playground.demo.listener;

import com.playground.demo.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PersonListener {

    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void listen(Person person) {
        if (0 < 1) {
            throw new RuntimeException("aaaah");
        }
        log.info("received kafka message {}", person);
    }

}
