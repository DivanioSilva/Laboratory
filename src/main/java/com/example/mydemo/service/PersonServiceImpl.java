package com.example.mydemo.service;

import com.example.mydemo.domain.Person;
import com.example.mydemo.domain.PersonDto;
import com.example.mydemo.domain.PersonWithIdDto;
import com.example.mydemo.exceptions.PersonNotFoundException;
import com.example.mydemo.mappers.PersonMapper;
import com.example.mydemo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public PersonWithIdDto savePerson(PersonDto personDto) {
        log.info("A salvar a entidade");
        Person person = this.personMapper.personDtoToPerson(personDto);
        Person savedPerson = this.personRepository.save(person);
        return this.personMapper.personToPersonWithIdDto(savedPerson);
    }

    @Override
    public PersonDto updatePerson(Long personId, PersonDto personDto) throws PersonNotFoundException {
        log.info("A fazer o update da entidade");
        Person dbPerson = personRepository.findById(personId).orElseThrow(PersonNotFoundException::new);
        this.personMapper.updatePersonFromPersonDto(personDto, dbPerson);
        return this.personMapper.personToPersonDto(personRepository.save(dbPerson));
    }

    @Override
    public PersonDto findById(Long personId) throws PersonNotFoundException {
        log.info("A procurar a entidade");

        return personMapper.personToPersonDto(
                personRepository.findById(personId).orElseThrow(PersonNotFoundException::new));
    }

    @Override
    public List<PersonWithIdDto> findAll() {
        return this.personMapper.personsToPersonsWithIdDto(this.personRepository.findAll());
    }

    @Override
    public PersonDto findByPersonFirstName(String firstName) throws PersonNotFoundException {
        Person person = this.personRepository.findByFirstName(firstName).orElseThrow(PersonNotFoundException::new);
        return this.personMapper.personToPersonDto(person);

    }
}