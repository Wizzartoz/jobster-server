package com.jobster.website.repositories;

import com.jobster.website.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {
    Optional<Person> findByLogin(String username);
}
