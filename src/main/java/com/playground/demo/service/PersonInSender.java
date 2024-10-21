package com.playground.demo.service;

import com.playground.demo.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonInSender implements BiConsumer<SendResult<Void, Person>, Throwable> {
    private final KafkaTemplate<Void, Person> inKafkaTemplate;


    public void sendPerson(Person person) {
        inKafkaTemplate.sendDefault(person).whenComplete(this::accept);
    }

    @Override
    public void accept(SendResult<Void, Person> sendResult,
                       Throwable e) {
        if (e != null) {
            log.error("Failed to send kafka message", e);
        }
    }
}
