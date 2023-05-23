package com.nhnacademy.operationcivil.controller;

import com.nhnacademy.operationcivil.Service.HouseholdService;
import com.nhnacademy.operationcivil.Service.HouseholdServiceImpl;
import com.nhnacademy.operationcivil.dto.PostHouseholdDto;
import com.nhnacademy.operationcivil.dto.PostHouseholdMovementAddressDto;
import com.nhnacademy.operationcivil.dto.PutHouseholdMovementAddressDto;
import com.nhnacademy.operationcivil.exception.NotEnoughParam;
import com.nhnacademy.operationcivil.exception.NotEnoughPathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping("/household")
@RequiredArgsConstructor
public class HouseholdRestController {
    private static final String HOUSEHOLD_SERIAL_NUMBER_PARAM_ERROR = "household serial number";
    private static final String HOUSEHOLD_SERIAL_NUMBER_OR_REPORT_DATE_PARAM_ERROR = "household serial number or report date";
    private final HouseholdService householdService;
    @PostMapping("")
    public PostHouseholdDto householdPost(@RequestBody @Valid PostHouseholdDto postHouseholdDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new NotEnoughParam();
        }
        return householdService.createHousehold(postHouseholdDto);
    }

    @DeleteMapping("/{householdSerialNumber}")
    public ResponseEntity<String> householdDelete(@PathVariable("householdSerialNumber") Long householdSerialNumber) {
        if (householdSerialNumber.equals(0L)) {
            throw new NotEnoughPathParam(HOUSEHOLD_SERIAL_NUMBER_PARAM_ERROR);
        }
        householdService.deleteHousehold(householdSerialNumber);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{householdSerialNumber}/movement")
    public PostHouseholdMovementAddressDto householdMovementAddressPost(@PathVariable("householdSerialNumber") Long householdSerialNumber,
                                               @RequestBody @Valid PostHouseholdMovementAddressDto postHouseholdMomentAddressDto, BindingResult bindingResult) {
        if (householdSerialNumber.equals(0L)) {
            throw new NotEnoughPathParam(HOUSEHOLD_SERIAL_NUMBER_PARAM_ERROR);
        }
        if (bindingResult.hasErrors()) {
            throw new NotEnoughParam();
        }
        return householdService.createHouseholdMomentAddressDto(householdSerialNumber, postHouseholdMomentAddressDto);
    }
    @PutMapping("/{householdSerialNumber}/movement/{reportDate}")
    public PutHouseholdMovementAddressDto householdMovementAddressPut(@PathVariable("householdSerialNumber") Long householdSerialNumber,
                                              @PathVariable("reportDate")@DateTimeFormat(pattern = "yyyyMMdd") LocalDate reportDate,
                                              @RequestBody @Valid PutHouseholdMovementAddressDto putHouseholdMomentAddressDto, BindingResult bindingResult) {
        if (householdSerialNumber.equals(0L) || Objects.isNull(reportDate)) {
            throw new NotEnoughPathParam(HOUSEHOLD_SERIAL_NUMBER_OR_REPORT_DATE_PARAM_ERROR);
        }
        if (bindingResult.hasErrors()) {
            throw new NotEnoughParam();
        }
        return householdService.modifyHouseholdMomentAddressDto(householdSerialNumber, putHouseholdMomentAddressDto, reportDate);
    }
    @DeleteMapping("/{householdSerialNumber}/movement/{reportDate}")
    public ResponseEntity<String> householdDelete(@PathVariable("householdSerialNumber") Long householdSerialNumber,
                                  @PathVariable("reportDate")@DateTimeFormat(pattern = "yyyyMMdd") LocalDate reportDate) {
        if (householdSerialNumber.equals(0L) || Objects.isNull(reportDate)) {
            throw new NotEnoughPathParam(HOUSEHOLD_SERIAL_NUMBER_OR_REPORT_DATE_PARAM_ERROR);
        }
        householdService.deleteHouseholdMovementAddressDto(householdSerialNumber,reportDate);
        return ResponseEntity.ok().build();
    }
}
