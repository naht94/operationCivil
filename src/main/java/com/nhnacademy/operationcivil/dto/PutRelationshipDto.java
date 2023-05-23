package com.nhnacademy.operationcivil.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PutRelationshipDto {
    @NotBlank(message = "가족 관계를 입력해주세요")
    private String relationShip;
}
