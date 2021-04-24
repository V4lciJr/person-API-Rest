package com.personapi.service;

import com.personapi.dto.request.PersonDTO;
import com.personapi.dto.response.MessageRespondeDTO;
import com.personapi.entity.Person;
import com.personapi.exceptions.PersonNotFoundException;
import com.personapi.mapper.PersonMapper;
import com.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @PostMapping
    public MessageRespondeDTO createPerson(PersonDTO personDTO){
        Person personToSaved = personMapper.toModel(personDTO);

        Person personSaved = personRepository.save(personToSaved);
        return createMessageResponse(personSaved.getId(), "Created person with ID");
    }


    public List<PersonDTO> listAll() {
       List<Person> allPersons = personRepository.findAll();

       return allPersons.stream().map(personMapper::toDTO).collect(Collectors.toList());
    }


    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyByExists(id);

        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyByExists(id);

        personRepository.deleteById(id);
    }

    public MessageRespondeDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyByExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatePerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatePerson.getId(), "Update person with ID");
    }

    private Person verifyByExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageRespondeDTO createMessageResponse(Long id, String message) {
        return MessageRespondeDTO
                .builder()
                .message(message + id)
                .build();
    }
}
