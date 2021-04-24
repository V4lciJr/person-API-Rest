package com.personapi.utils;

import com.personapi.dto.request.PhoneDTO;
import com.personapi.entity.Phone;
import com.personapi.enums.PhoneType;

public class PhoneUtils {

    private static final String PHONE_NUMBER = "(09)99234-3434";
    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
    private static final Long PHONE_ID = 1L;

    public static PhoneDTO createFakeDTO() {
        return PhoneDTO.builder()
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

    public static Phone createFakeEntity(){
        return Phone.builder()
                .id(PHONE_ID)
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }
}
