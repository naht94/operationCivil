package com.nhnacademy.operationcivil.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostResidentDto {
    @NotNull(message = "주민일련번호를 입력해주세요")
    private Long residentSerialNumber;
    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    @NotBlank(message = "주민번호를 입력하주세요")
    private String residentRegistrationNumber;
    @NotBlank(message = "성별")
    private String genderCode;
    @NotNull(message = "생일을 입력해주세요")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime birthDate;
    @NotBlank(message = "태어난 장소를 입력해주세요")
    private String birthPlaceCode;
    @NotBlank(message = "주소를 입력해주세요")
    private String registrationBaseAddress;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime deathDate;
    private String deathPlaceCode;
    private String deathPlaceAddress;
}
