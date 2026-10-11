package com.example.employee.controller;

import com.example.employee.model.Person;
import com.example.employee.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/generate")
    public List<Person> generatePeople(
            @RequestParam(defaultValue = "500") int count) {

        return personService.generateAndSave(count);
    }

    @GetMapping
    public List<Person> getAllPeople() {

        return personService.getAllPeople();
    }

    @GetMapping("/{id}")
    public Person getPersonById(
            @PathVariable String id) {

        return personService.getPersonById(id);
    }

    @GetMapping("/search")
    public Object searchPeople(
            @RequestParam(required = false)
            String employeeId,

            @RequestParam(required = false)
            String name,

            @RequestParam(required = false)
            String role,

            @RequestParam(required = false)
            String address,

            @RequestParam(required = false)
            String state) {

        return personService.searchPeople(
                employeeId,
                name,
                role,
                address,
                state
        );
    }
}