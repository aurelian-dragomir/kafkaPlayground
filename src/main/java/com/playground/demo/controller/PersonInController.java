package com.playground.demo.controller;

import com.playground.demo.model.Person;
import com.playground.demo.service.PersonInSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonInController {
    private final PersonInSender personInSender;

    @PostMapping
    public void sendPersonDetails(@RequestBody Person person) {
        personInSender.sendPerson(person);
    }

}
