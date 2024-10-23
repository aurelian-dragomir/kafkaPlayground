package com.playground.demo.service;

import com.playground.demo.entity.PersonEntity;
import com.playground.demo.model.Person;
import com.playground.demo.repository.PersonEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonEntityService {
    private final PersonEntityRepository personEntityRepository;

    @Transactional
    public void save(Person person) {
        personEntityRepository.save(new PersonEntity(person.getName()));
    }
}
