package com.personapi.service;

import com.personapi.controller.PersonController;
import com.personapi.dto.request.PersonDTO;
import com.personapi.dto.response.MessageRespondeDTO;
import com.personapi.entity.Person;
import com.personapi.exceptions.PersonNotFoundException;
import com.personapi.mapper.PersonMapper;
import com.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    public MessageRespondeDTO createPerson(PersonDTO personDTO){
        Person personToSaved = personMapper.toModel(personDTO);

        Person personSaved = personRepository.save(personToSaved);
        return MessageRespondeDTO
                .builder()
                .message("Created person with ID " + personSaved.getId())
                .build();
    }


    public List<PersonDTO> listAll() {
       List<Person> allPersons = personRepository.findAll();

       return allPersons.stream().map(personMapper::toDTO).collect(Collectors.toList());
    }


    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Optional<Person> optionalPerson = personRepository.findById(id);

        if(optionalPerson.isEmpty()){
            throw new PersonNotFoundException(id);
        }

        return personMapper.toDTO(optionalPerson.get());
    }
}
