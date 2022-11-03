package com.jobster.website.utils;

import com.jobster.website.exeptions.PersonNotFoundException;
import com.jobster.website.models.Person;
import com.jobster.website.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UniqueLoginValidator implements Validator {

    private final PersonRepository personRepository;

    @Autowired
    public UniqueLoginValidator(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        try {
            personRepository.findByLogin(person.getLogin())
                    .orElseThrow(()-> new PersonNotFoundException(""));
            errors.rejectValue("login", "", "This login is already exist");
        } catch (PersonNotFoundException ignored) {

        }
    }
}
