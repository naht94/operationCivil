package com.nhnacademy.operationcivil.controller;

import com.nhnacademy.operationcivil.Service.ResidentService;
import com.nhnacademy.operationcivil.Service.ResidentServiceImpl;
import com.nhnacademy.operationcivil.dto.PostBirthDto;
import com.nhnacademy.operationcivil.dto.PostDeathDto;
import com.nhnacademy.operationcivil.dto.PostResidentDto;
import com.nhnacademy.operationcivil.dto.PutBirthDto;
import com.nhnacademy.operationcivil.dto.PutDeathDto;
import com.nhnacademy.operationcivil.dto.PutRelationshipDto;
import com.nhnacademy.operationcivil.dto.PutResidentDto;
import com.nhnacademy.operationcivil.enumdata.BirthDeathCode;
import com.nhnacademy.operationcivil.exception.NotEnoughPathParam;
import com.nhnacademy.operationcivil.exception.NotEnoughParam;
import com.nhnacademy.operationcivil.dto.PostRelationshipDto;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("/residents")
@RequiredArgsConstructor
public class ResidentRestController {
    private final ResidentService residentService;
    private static final String SERIAL_NUMBER_PARAM_ERROR = "serial number";
    private static final String SERIAL_NUMBER_OR_FAMILY_SERIAL_NUMBER_PARAM_ERROR = "serial number or family serial number";
    private static final String SERIAL_NUMBER_OR_TARGET_SERIAL_NUMBER_PARAM_ERROR = "serial number or target serial number";

    @PostMapping("")
    public PostResidentDto ResidentPost(@RequestBody @Valid PostResidentDto postResidentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new NotEnoughParam();
        }
        return residentService.createResident(postResidentDto);
    }

    @PutMapping("/{serialNumber}")
    public PutResidentDto ResidentPut(@PathVariable("serialNumber") Long serialNumber,
                                      @RequestBody @Valid PutResidentDto putResidentDto, BindingResult bindingResult) {
        if (serialNumber.equals(0L)) {
            throw new NotEnoughPathParam(SERIAL_NUMBER_PARAM_ERROR);
        }
        if (bindingResult.hasErrors()) {
            throw new NotEnoughParam();
        }
        return residentService.modifyResident(serialNumber, putResidentDto);
    }
    @PostMapping(path ="/{serialNumber}/relationship")
    public PostRelationshipDto relationshipPost(@PathVariable("serialNumber") Long serialNumber,
                                                @RequestBody @Valid PostRelationshipDto postRelationshipDto, BindingResult bindingResult) {
        if (serialNumber.equals(0L)) {
            throw new NotEnoughPathParam(SERIAL_NUMBER_PARAM_ERROR);
        }
        if (bindingResult.hasErrors()) {
            throw new NotEnoughParam();
        }
        return residentService.createFamilyRelationship(serialNumber,
                postRelationshipDto.getFamilySerialNumber(), postRelationshipDto.getRelationShip());
    }

    @PutMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public PutRelationshipDto relationshipPut(@PathVariable("serialNumber") Long serialNumber,
                                              @PathVariable("familySerialNumber") Long familySerialNumber,
                                              @RequestBody@Valid PutRelationshipDto putRelationshipDto, BindingResult bindingResult) {
        if (serialNumber.equals(0L) || familySerialNumber.equals(0L)) {
            throw new NotEnoughPathParam(SERIAL_NUMBER_OR_FAMILY_SERIAL_NUMBER_PARAM_ERROR);
        }
        if (bindingResult.hasErrors()) {
            throw new NotEnoughParam();
        }

        return residentService.modifyFamilyRelationship(serialNumber, familySerialNumber, putRelationshipDto.getRelationShip());
    }

    @DeleteMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity<String> relationshipDelete(@PathVariable("serialNumber") Long serialNumber,
                                             @PathVariable("familySerialNumber") Long familySerialNumber) {
        if (serialNumber.equals(0L) || familySerialNumber.equals(0L)) {
            throw new NotEnoughPathParam(SERIAL_NUMBER_OR_FAMILY_SERIAL_NUMBER_PARAM_ERROR);
        }
        residentService.deleteFamilyRelationship(serialNumber, familySerialNumber);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{serialNumber}/birth")
    public PostBirthDto birthPost(@PathVariable("serialNumber") Long serialNumber,
                                  @RequestBody@Valid PostBirthDto postBirthDto, BindingResult bindingResult) {
        if (serialNumber.equals(0L)) {
            throw new NotEnoughPathParam(SERIAL_NUMBER_PARAM_ERROR);
        }
        if (bindingResult.hasErrors()) {
            throw new NotEnoughParam();
        }
        return residentService.createBirthReportResident(serialNumber, postBirthDto);
    }

    @PutMapping("/{serialNumber}/birth/{targetSerialNumber}")
    public PutBirthDto birthModify(@PathVariable("serialNumber") Long serialNumber,
                                   @PathVariable("targetSerialNumber") Long targetSerialNumber,
                                   @RequestBody@Valid PutBirthDto putBirthDto, BindingResult bindingResult) {
        if (serialNumber.equals(0L) || targetSerialNumber.equals(0L)) {
            throw new NotEnoughPathParam(SERIAL_NUMBER_OR_TARGET_SERIAL_NUMBER_PARAM_ERROR);
        }
        if (bindingResult.hasErrors()) {
            throw new NotEnoughParam();
        }
        return residentService.modifyBirthReportResident(serialNumber, putBirthDto, targetSerialNumber);
    }

    @DeleteMapping("/{serialNumber}/birth/{targetSerialNumber}")
    public ResponseEntity<String> birthDelete(@PathVariable("serialNumber") Long serialNumber,
                              @PathVariable("targetSerialNumber") Long targetSerialNumber) {
        if (serialNumber.equals(0L) || targetSerialNumber.equals(0L)) {
            throw new NotEnoughPathParam(SERIAL_NUMBER_OR_TARGET_SERIAL_NUMBER_PARAM_ERROR);
        }
        residentService.deleteBirthReportResident(serialNumber, BirthDeathCode.출생, targetSerialNumber);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{serialNumber}/death")
    public PostDeathDto deathPost(@PathVariable("serialNumber") Long serialNumber,
                                  @RequestBody @Valid PostDeathDto postDeathDto, BindingResult bindingResult) {
        if (serialNumber.equals(0L)) {
            throw new NotEnoughPathParam(SERIAL_NUMBER_PARAM_ERROR);
        }
        if (bindingResult.hasErrors()) {
            throw new NotEnoughParam();
        }

        return residentService.createBirthReportResident(serialNumber, postDeathDto);
    }

    @PutMapping("/{serialNumber}/death/{targetSerialNumber}")
    public PutDeathDto deathModify(@PathVariable("serialNumber") Long serialNumber,
                                   @PathVariable("targetSerialNumber") Long targetSerialNumber,
                                   @RequestBody@Valid PutDeathDto putDeathDto, BindingResult bindingResult) {
        if (serialNumber.equals(0L) || targetSerialNumber.equals(0L)) {
            throw new NotEnoughPathParam(SERIAL_NUMBER_OR_TARGET_SERIAL_NUMBER_PARAM_ERROR);
        }
        if (bindingResult.hasErrors()) {
            throw new NotEnoughParam();
        }
        return residentService.modifyBirthReportResident(serialNumber,putDeathDto,targetSerialNumber);
    }

    @DeleteMapping("/{serialNumber}/death/{targetSerialNumber}")
    public ResponseEntity<String> deathDelete(@PathVariable("serialNumber") Long serialNumber,
                              @PathVariable("targetSerialNumber") Long targetSerialNumber) {
        if (serialNumber.equals(0L) || targetSerialNumber.equals(0L)) {
            throw new NotEnoughPathParam(SERIAL_NUMBER_OR_TARGET_SERIAL_NUMBER_PARAM_ERROR);
        }
        residentService.deleteBirthReportResident(serialNumber, BirthDeathCode.사망, targetSerialNumber);
        return ResponseEntity.ok().build();
    }
}
