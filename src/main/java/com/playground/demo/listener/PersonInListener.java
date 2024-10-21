package com.playground.demo.listener;

import com.playground.demo.model.Person;
import com.playground.demo.service.PersonOutSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class PersonInListener {
    private PersonOutSender personOutSender;

    public PersonInListener(PersonOutSender personOutSender) {
        this.personOutSender = personOutSender;
    }

    @KafkaListener(topics = "${spring.kafka.producer.in.topic}")
    @Transactional
    public void listen(Person person) {
//        if (0 < 1) {
//            throw new RuntimeException("aaaah");
//        }
        personOutSender.send(person);
        log.info("received kafka message {}", person);
    }

}
