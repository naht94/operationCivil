package com.nhnacademy.operationcivil.dto;

import org.springframework.beans.factory.annotation.Value;

public interface ResidentDto {
    @Value("#{target.residentSerialNumber}")
    Long getResidentSerialNumber();

    String getName();
    @Value("#{target.residentRegistrationNumber}")
    String getResidentRegistrationNumber();
    String getGenderCode();
}
