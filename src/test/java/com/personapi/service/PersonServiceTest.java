package com.personapi.service;


import com.personapi.dto.request.PersonDTO;
import com.personapi.dto.response.MessageRespondeDTO;
import com.personapi.entity.Person;
import com.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.personapi.utils.PersonUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage(){
        PersonDTO personDTO = createFakePersonDTO();
        Person expectedSavedPerson = createFakePerson();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageRespondeDTO expectedSuccessMessage = createExpectedResponse(expectedSavedPerson.getId());

        MessageRespondeDTO sucessMessage = personService.createPerson(personDTO);

        assertEquals(expectedSuccessMessage, sucessMessage);
    }

    private MessageRespondeDTO createExpectedResponse(Long id) {
        return MessageRespondeDTO
                .builder()
                .message("Created person with ID" + id)
                .build();
    }

}
