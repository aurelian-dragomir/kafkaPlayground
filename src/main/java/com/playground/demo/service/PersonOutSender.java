package com.playground.demo.service;

import com.playground.demo.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PersonOutSender {
    private final KafkaTemplate<Void, Person> outPersonKafkaTemplate;

    //    @Transactional("outPersonKafkaTransactionManager")
    @Transactional
    public void send(Person person) {
        outPersonKafkaTemplate.sendDefault(person);
    }
}
