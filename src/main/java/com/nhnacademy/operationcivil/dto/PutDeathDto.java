package com.nhnacademy.operationcivil.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PutDeathDto {
    private LocalDate birthDeathReportDate;
    private String phoneNumber;
    private String emailAddress;
    private String deathReportQualificationsCode;
}
