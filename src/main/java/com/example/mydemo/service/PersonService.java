package com.example.mydemo.service;

import com.example.mydemo.domain.PersonDto;
import com.example.mydemo.domain.PersonWithIdDto;
import com.example.mydemo.exceptions.PersonNotFoundException;

import java.util.List;

public interface PersonService {
    PersonWithIdDto savePerson(PersonDto personDto);

    PersonDto updatePerson(Long personId, PersonDto personDto) throws PersonNotFoundException;

    PersonDto findById(Long personId) throws PersonNotFoundException;
    List<PersonWithIdDto> findAll();
    PersonDto findByPersonFirstName(String firstName) throws PersonNotFoundException;
}