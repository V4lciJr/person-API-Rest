package com.personapi.service;

import com.personapi.dto.response.MessageRespondeDTO;
import com.personapi.entity.Person;
import com.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    public MessageRespondeDTO createPerson(Person person){
        Person personSaved = personRepository.save(person);
        return MessageRespondeDTO
                .builder()
                .message("Created person with ID " + personSaved.getId())
                .build();
    }


}
