package com.personapi.utils;

import com.personapi.dto.request.PersonDTO;
import com.personapi.entity.Person;

import java.time.LocalDate;
import java.util.Collections;

public class PersonUtils {

    private static final String FIRST_NAME = "Valci";
    private static final String LAST_NAME = "Júnior";
    private static final String CPF_NUMBER = "001.002.007-01";
    private static final Long PERSON_ID = 1L;
    public static final String BIRTH_DATE = LocalDate.now().toString();

    public static PersonDTO createFakePersonDTO(){
        return PersonDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .birthDate(BIRTH_DATE)
                .phones(Collections.singletonList(PhoneUtils.createFakeDTO()))
                .build();
    }

    public static Person createFakePerson(){
        return Person.builder()
                .id(PERSON_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .birthDate(LocalDate.now())
                .phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
                .build();
    }
}


