package com.playground.demo.service;

import com.playground.demo.model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PersonOutSender {
    private KafkaTemplate<Void, Person> outPersonKafkaTemplate;

    public PersonOutSender(@Qualifier("outPersonKafkaTemplate") KafkaTemplate<Void, Person> outPersonKafkaTemplate) {
        this.outPersonKafkaTemplate = outPersonKafkaTemplate;
    }

    //    @Transactional("outPersonKafkaTransactionManager")
    @Transactional
    public void send(Person person) {
        outPersonKafkaTemplate.sendDefault(person);
    }
}
