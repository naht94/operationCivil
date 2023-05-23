package com.nhnacademy.operationcivil.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostHouseholdDto {
    @NotNull(message = "세대 일련번호를 입력해주세요")
    private Long householdSerialNumber;
    @NotNull(message = "세대주 주민 일련번호를 입력해주세요")
    private Long residentSerialNumber;
    @NotNull(message = "세대 구성일자를 입력해주세요")
    private LocalDate householdCompositionDate;
    @NotBlank(message = "세대구성 사유를 입력해주세요")
    private String householdCompositionReasonCode;
    @NotBlank(message = "현재 전입주소를 입력해주세요")
    private String currentHouseMovementAddress;
}
