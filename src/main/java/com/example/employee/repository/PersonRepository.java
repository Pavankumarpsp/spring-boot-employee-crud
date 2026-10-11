package com.example.employee.repository;

import com.example.employee.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {

    Person findTopByOrderByEmployeeIdDesc();
}