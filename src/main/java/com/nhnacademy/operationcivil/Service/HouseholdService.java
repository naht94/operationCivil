package com.nhnacademy.operationcivil.Service;

import com.nhnacademy.operationcivil.dto.HouseholdCertificationDto;
import com.nhnacademy.operationcivil.dto.PostHouseholdDto;
import com.nhnacademy.operationcivil.dto.PostHouseholdMovementAddressDto;
import com.nhnacademy.operationcivil.dto.PutHouseholdMovementAddressDto;
import com.nhnacademy.operationcivil.entity.Household;
import com.nhnacademy.operationcivil.entity.HouseholdMovementAddress;

import java.time.LocalDate;
import java.util.List;

public interface HouseholdService {
    PostHouseholdDto createHousehold(PostHouseholdDto postHouseholdDto);

    void deleteHousehold(Long householdSerialNumber);

    PostHouseholdMovementAddressDto createHouseholdMomentAddressDto(Long householdSerialNumber, PostHouseholdMovementAddressDto postHouseholdMomentAddressDto);

    PutHouseholdMovementAddressDto modifyHouseholdMomentAddressDto(Long householdSerialNumber, PutHouseholdMovementAddressDto putHouseholdMomentAddressDto, LocalDate houseMovementReportDate);

    void deleteHouseholdMovementAddressDto(Long householdSerialNumber, LocalDate houseMovementReportDate);

    List<HouseholdCertificationDto> getCertification(Long householdSerialNumber);

    Long getHouseholdSerialNumber(Long residentSerialNumber);

    List<HouseholdMovementAddress> getMovementAddress(Long householdSerialNumber);
    Household getHousehold(Long householdSerialNumber);
}
