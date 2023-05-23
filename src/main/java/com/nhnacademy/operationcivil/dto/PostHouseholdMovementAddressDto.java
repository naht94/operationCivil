package com.nhnacademy.operationcivil.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostHouseholdMovementAddressDto {

    @NotBlank(message = "전입주소를 입력해주세요")
    private String houseMovementAddress;
    private String lastAddressYn;
    @NotNull(message = "전입신고일자를 입력해주세요")
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate houseMovementReportDate;
}
