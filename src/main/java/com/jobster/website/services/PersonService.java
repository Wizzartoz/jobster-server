package com.jobster.website.services;

import com.jobster.website.exeptions.PersonNotFoundException;
import com.jobster.website.models.Person;
import com.jobster.website.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public Person findByLogin(String login) {
       return personRepository.findByLogin(login)
                .orElseThrow(() -> new PersonNotFoundException("Person with login: " + login + " not found"));
    }

    public Person update(Person person) {
        return personRepository.save(person);
    }



}
