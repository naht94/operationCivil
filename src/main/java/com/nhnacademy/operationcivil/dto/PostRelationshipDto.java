package com.nhnacademy.operationcivil.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRelationshipDto {
    @NotNull(message = "가족 일련번호가 없습니다")
    private Long familySerialNumber;
    @NotBlank(message = "가족 관계를 입력해주세요")
    private String relationShip;
}
