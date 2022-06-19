package com.example.mydemo.mappers;

import com.example.mydemo.domain.Person;
import com.example.mydemo.domain.PersonDto;
import com.example.mydemo.domain.PersonWithIdDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface PersonMapper {
    Person personDtoToPerson(PersonDto personDto);

    PersonDto personToPersonDto(Person person);
    
    PersonWithIdDto personToPersonWithIdDto(Person person);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePersonFromPersonDto(PersonDto personDto, @MappingTarget Person person);

    List<PersonWithIdDto> personsToPersonsWithIdDto(List<Person> all);
}
