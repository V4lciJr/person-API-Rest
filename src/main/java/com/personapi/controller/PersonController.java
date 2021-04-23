package com.personapi.controller;

import com.personapi.dto.request.PersonDTO;
import com.personapi.dto.response.MessageRespondeDTO;
import com.personapi.entity.Person;
import com.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageRespondeDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }
}
