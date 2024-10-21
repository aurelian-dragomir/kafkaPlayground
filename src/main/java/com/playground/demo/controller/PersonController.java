package com.playground.demo.controller;

import com.playground.demo.model.Person;
import com.playground.demo.service.PersonInSender;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonInSender kafkaInSender;

    @PostMapping
    public void sendPersonDetails(@RequestBody Person person) {
        kafkaInSender.sendPerson(person);
    }

}
