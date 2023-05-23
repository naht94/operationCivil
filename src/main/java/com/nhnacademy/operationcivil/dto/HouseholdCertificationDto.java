package com.nhnacademy.operationcivil.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class HouseholdCertificationDto{
    private String householdRelationshipCode;
    private String name;
    private String residentRegistrationNumber;
    private LocalDate reportDate;
    private String  householdCompositionChangeReasonCode;
    @QueryProjection
    public HouseholdCertificationDto(String householdRelationshipCode, String name, String residentRegistrationNumber, LocalDate reportDate, String householdCompositionChangeReasonCode) {
        this.householdRelationshipCode = householdRelationshipCode;
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.reportDate = reportDate;
        this.householdCompositionChangeReasonCode = householdCompositionChangeReasonCode;
    }
}
