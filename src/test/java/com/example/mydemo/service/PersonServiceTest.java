package com.example.mydemo.service;

import com.example.mydemo.domain.Person;
import com.example.mydemo.domain.PersonDto;
import com.example.mydemo.domain.PersonWithIdDto;
import com.example.mydemo.exceptions.PersonNotFoundException;
import com.example.mydemo.mappers.PersonMapper;
import com.example.mydemo.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonServiceTest {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonMapper personMapper;
    @MockBean
    private PersonRepository personRepository;

    @Test
    @DisplayName("Should saves the person")
    public void testSavePerson() {
        PersonDto personDto = new PersonDto("John", "Doe", 20);
        Person person = this.personMapper.personDtoToPerson(personDto);
        when(this.personRepository.save(person)).thenReturn(person);

        PersonWithIdDto savedPerson = this.personService.savePerson(personDto);

        assertEquals(savedPerson.getFirstName(), personDto.getFirstName());
        assertEquals(savedPerson.getLastName(), personDto.getLastName());
        assertEquals(savedPerson.getAge(), personDto.getAge());
    }

    @Test
    @DisplayName("Should throws an exception when the person is not found")
    public void testSavePersonWhenPersonIsNotFoundThenThrowsException() {
        PersonDto personDto = new PersonDto("John", "Doe", 20);
        Person person = this.personMapper.personDtoToPerson(personDto);
        when(this.personRepository.save(person)).thenReturn(person);

        PersonWithIdDto savedPerson = this.personService.savePerson(personDto);

        assertEquals(savedPerson.getFirstName(), personDto.getFirstName());
        assertEquals(savedPerson.getLastName(), personDto.getLastName());
        assertEquals(savedPerson.getAge(), personDto.getAge());
    }

    @Test
    @DisplayName("Should returns all the persons")
    public void testFindAllShouldReturnsAllPersons() {
        Person person = new Person(1L, "John", "Doe", 20);
        Person person2 = new Person(2L,"Jane", "Doe", 20);
        List<Person> persons = Arrays.asList(person, person2);
        when(personRepository.findAll()).thenReturn(persons);

        List<PersonWithIdDto> allPersons = personService.findAll();

        assertEquals(2, allPersons.size());
        assertEquals(personMapper.personToPersonWithIdDto(person), allPersons.get(0));
        assertEquals(personMapper.personToPersonWithIdDto(person2), allPersons.get(1));
    }

    @Test
    @DisplayName("Should return the person when the person exists")
    public void testFindByIdWhenPersonExistsThenReturnPerson() throws PersonNotFoundException {
        Person person = new Person(1L, "John", "Doe", 20);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        PersonDto personDto = personService.findById(1L);

        assertEquals(personMapper.personToPersonDto(person), personDto);
    }

    @Test
    @DisplayName("Should throw an exception when the person does not exist")
    public void testFindByIdWhenPersonDoesNotExistThenThrowException() {
        Long personId = 1L;
        when(personRepository.findById(personId)).thenReturn(Optional.empty());

        assertThrows(
                PersonNotFoundException.class,
                () -> {
                    personService.findById(personId);
                });
    }

    @Test
    @DisplayName("Should update the person when the person exists")
    public void testUpdatePersonWhenPersonExists() throws PersonNotFoundException {
        PersonDto personDto = new PersonDto("John", "Doe", 20);
        Person person = this.personMapper.personDtoToPerson(personDto);
        person.setId(1L);
        when(this.personRepository.findById(1L)).thenReturn(Optional.of(person));
        Person personToReturn = new Person(1L, "Johon", "Doe", 30);
        when(this.personRepository.save(person)).thenReturn(personToReturn);
        PersonDto updatedPerson =
                this.personService.updatePerson(1L, new PersonDto("John", "Doe", 30));

        assertEquals(30, updatedPerson.getAge());
    }

    @Test
    @DisplayName("Should throws an exception when the person does not exist")
    public void testUpdatePersonWhenPersonDoesNotExistThenThrowsException() {
        PersonDto personDto = new PersonDto("John", "Doe", 20);
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                PersonNotFoundException.class,
                () -> {
                    personService.updatePerson(1L, personDto);
                });
    }

    @Test
    void savePerson() {
        PersonDto personDto = PersonDto
                .builder()
                .age(11)
                .firstName("Daniel")
                .lastName("Silva")
                .build();

        Person person = new Person();
        this.personMapper.updatePersonFromPersonDto(personDto, person);
        Person personResponse = Person.builder()
                        .age(personDto.getAge())
                        .firstName(personDto.getFirstName())
                        .lastName(personDto.getLastName())
                        .id(1L)
                        .build();
        when(personRepository.save(person)).thenReturn(personResponse);
        PersonWithIdDto personWithIdDto = personService.savePerson(personDto);
        assertEquals(1L, personWithIdDto.getId());
        assertEquals(personDto.getFirstName(), personWithIdDto.getFirstName());
        assertEquals(personDto.getLastName(), personWithIdDto.getLastName());
    }

    @Test
    @DisplayName("Should return the person when the first name is found")
    public void testFindByPersonFirstNameWhenFirstNameIsFoundThenReturnThePerson() throws PersonNotFoundException {
        String firstName = "John";
        Person person = new Person(1L,"John", "Doe", 20);
        when(personRepository.findByFirstName(firstName)).thenReturn(Optional.of(person));

        PersonDto personDto = personService.findByPersonFirstName(firstName);

        assertEquals(person.getFirstName(), personDto.getFirstName());
        assertEquals(person.getLastName(), personDto.getLastName());
        assertEquals(person.getAge(), personDto.getAge());
    }

    @Test
    @DisplayName("Should throw an exception when the first name is not found")
    public void testFindByPersonFirstNameWhenFirstNameIsNotFoundThenThrowsException() {
        String firstName = "John";
        when(personRepository.findByFirstName(firstName)).thenReturn(Optional.empty());

        assertThrows(
                PersonNotFoundException.class,
                () -> {
                    personService.findByPersonFirstName(firstName);
                });
    }
}
