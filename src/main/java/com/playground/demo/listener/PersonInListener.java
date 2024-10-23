package com.playground.demo.listener;

import com.playground.demo.model.Person;
import com.playground.demo.service.PersonEntityService;
import com.playground.demo.service.PersonOutSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class PersonInListener {
    private final PersonOutSender personOutSender;
    private final PersonEntityService personEntityService;

    @KafkaListener(topics = "${spring.kafka.producer.in.topic}")
//    @Transactional("outPersonKafkaTransactionManager")
    @Transactional
    public void listen(Person person) {
//        if (0 < 1) {
//            throw new RuntimeException("before producer send");
//        }

        personOutSender.send(person);

//        if (0 < 1) {
//            throw new RuntimeException("after producer send");
//        }

//        personEntityService.save(person);
        log.info("received kafka message {}", person);
    }

}
