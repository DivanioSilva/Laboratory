package com.example.mydemo.controller;

import com.example.mydemo.domain.PersonDto;
import com.example.mydemo.domain.PersonWithIdDto;
import com.example.mydemo.exceptions.PersonNotFoundException;
import com.example.mydemo.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/person")
@RestController
public class PersonController {
    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonWithIdDto savePerson(@RequestBody PersonDto person) {
        return service.savePerson(person);
    }

    @PutMapping(value = "{personId}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto updatePerson(@PathVariable("personId") Long personId, @RequestBody PersonDto person)
            throws PersonNotFoundException {
        return service.updatePerson(personId, person);
    }

    @GetMapping(value = "{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto findPersonById(@PathVariable("personId") Long personId)
            throws PersonNotFoundException {
        return service.findById(personId);
    }

    @GetMapping(value = "/first-name/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto findPersonByFirstName(@PathVariable("firstName") String firstName)
            throws PersonNotFoundException {
        return service.findByPersonFirstName(firstName);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonWithIdDto> findAll(){
        return this.service.findAll();
    }
}