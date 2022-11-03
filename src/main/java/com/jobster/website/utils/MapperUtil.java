package com.jobster.website.utils;

import com.jobster.website.dtos.PersonDto;
import com.jobster.website.models.Person;
import lombok.experimental.UtilityClass;
@UtilityClass
public class MapperUtil {
    public Person personDtoToPerson(PersonDto dto) {
        Person person = new Person();
        person.setLogin(dto.getLogin());
        person.setPassword(dto.getPassword());
        person.setEmail(dto.getEmail());
        person.setRole(dto.getRole());
        return person;
    }
}
