package com.example.employee.service;

import com.example.employee.model.Person;
import com.example.employee.model.StateEmployeeResponse;
import com.example.employee.repository.PersonRepository;
import net.datafaker.Faker;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final MongoTemplate mongoTemplate;

    private final Faker faker =
            new Faker(new Locale("en", "IN"));

    public PersonService(
            PersonRepository personRepository,
            MongoTemplate mongoTemplate) {

        this.personRepository = personRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Person> generateAndSave(int count) {

        if (count <= 0) {
            throw new IllegalArgumentException(
                    "Count must be greater than 0"
            );
        }

        Person lastPerson =
                personRepository.findTopByOrderByEmployeeIdDesc();

        int nextEmployeeNumber = 1;

        if (lastPerson != null &&
                lastPerson.getEmployeeId() != null) {

            String lastEmployeeId =
                    lastPerson.getEmployeeId();

            String numberPart =
                    lastEmployeeId.replace("EMP", "");

            nextEmployeeNumber =
                    Integer.parseInt(numberPart) + 1;
        }

        List<Person> people = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            String employeeId = String.format(
                    "EMP%06d",
                    nextEmployeeNumber++
            );

            String name =
                    faker.name().fullName();

            String role =
                    faker.job().title();

            String street =
                    faker.address().streetAddress();

            String city =
                    faker.address().city();

            String state =
                    faker.address().stateAbbr();

            String pinCode =
                    faker.address().postcode();

            String address =
                    street
                            + ", "
                            + city
                            + ", "
                            + state
                            + " "
                            + pinCode;

            Person person = new Person(
                    employeeId,
                    name,
                    role,
                    address,
                    state
            );

            people.add(person);
        }

        return personRepository.saveAll(people);
    }

    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    public Person getPersonById(String id) {

        return personRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Person not found with id: " + id
                        )
                );
    }

    public Object searchPeople(
            String employeeId,
            String name,
            String role,
            String address,
            String state) {

        if (state != null && !state.isBlank()) {

            Query stateQuery = new Query(
                    Criteria.where("state")
                            .is(state.trim().toUpperCase())
            );

            List<Person> people =
                    mongoTemplate.find(
                            stateQuery,
                            Person.class
                    );

            return new StateEmployeeResponse(
                    people.size(),
                    people
            );
        }

        Query query = new Query();

        List<Criteria> criteriaList =
                new ArrayList<>();

        if (employeeId != null &&
                !employeeId.isBlank()) {

            criteriaList.add(
                    Criteria.where("employeeId")
                            .is(employeeId)
            );
        }

        if (name != null &&
                !name.isBlank()) {

            criteriaList.add(
                    Criteria.where("name")
                            .regex(
                                    Pattern.quote(name),
                                    "i"
                            )
            );
        }

        if (role != null &&
                !role.isBlank()) {

            criteriaList.add(
                    Criteria.where("role")
                            .regex(
                                    Pattern.quote(role),
                                    "i"
                            )
            );
        }

        if (address != null &&
                !address.isBlank()) {

            criteriaList.add(
                    Criteria.where("address")
                            .regex(
                                    Pattern.quote(address),
                                    "i"
                            )
            );
        }

        if (!criteriaList.isEmpty()) {

            query.addCriteria(
                    new Criteria().andOperator(
                            criteriaList.toArray(
                                    new Criteria[0]
                            )
                    )
            );
        }

        return mongoTemplate.find(
                query,
                Person.class
        );
    }
}