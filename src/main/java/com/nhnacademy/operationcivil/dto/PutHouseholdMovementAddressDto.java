package com.nhnacademy.operationcivil.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PutHouseholdMovementAddressDto {
    private String houseMovementAddress;
    private String lastAddressYn;
}
