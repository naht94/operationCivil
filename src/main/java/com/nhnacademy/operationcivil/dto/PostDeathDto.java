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
public class PostDeathDto {
    @NotNull(message = "사망신고일을 입력해주세요")
    private LocalDate birthDeathReportDate;
    @NotBlank(message = "전화번호를 입력해주세요")
    private String phoneNumber;
    @NotBlank(message = "출생사망 타입코드를 입력해주세요")
    private String birthDeathTypeCode;
    @NotNull(message = "등록대상 시리얼넘버를 입력해주세요")
    private Long residentSerialNumber;
    private String emailAddress;
    private String deathReportQualificationsCode;

}
