package com.nhnacademy.operationcivil.Service;

import com.nhnacademy.operationcivil.dto.PostBirthDto;
import com.nhnacademy.operationcivil.dto.PostDeathDto;
import com.nhnacademy.operationcivil.dto.PostRelationshipDto;
import com.nhnacademy.operationcivil.dto.PostResidentDto;
import com.nhnacademy.operationcivil.dto.PutBirthDto;
import com.nhnacademy.operationcivil.dto.PutDeathDto;
import com.nhnacademy.operationcivil.dto.PutRelationshipDto;
import com.nhnacademy.operationcivil.dto.PutResidentDto;
import com.nhnacademy.operationcivil.entity.BirthDeathReportResident;
import com.nhnacademy.operationcivil.entity.FamilyRelationship;
import com.nhnacademy.operationcivil.entity.Resident;
import com.nhnacademy.operationcivil.enumdata.BirthDeathCode;
import com.nhnacademy.operationcivil.enumdata.FamilyRelation;

import java.time.LocalDate;
import java.util.List;

public interface ResidentService {
    PostResidentDto createResident(PostResidentDto postResidentDto);
    PutResidentDto modifyResident(Long residentSerialNumber,
                                  PutResidentDto putResidentDto);

    void deleteResident(Long residentSerialNumber);
    PostRelationshipDto createFamilyRelationship(Long baseResidentSerialNumber,
                                                 Long familyResidentSerialNumber,
                                                 String familyRelationshipCode);

    PutRelationshipDto modifyFamilyRelationship(Long baseResidentSerialNumber,
                                                Long familyResidentSerialNumber,
                                                String familyRelationshipCode);
    void deleteFamilyRelationship(Long baseResidentSerialNumber,
                                  Long familyResidentSerialNumber);
    PostBirthDto createBirthReportResident(Long reportResidentSerialNumber,
                                           PostBirthDto postBirthDto);
    PutBirthDto modifyBirthReportResident(Long reportResidentSerialNumber,
                                          PutBirthDto putBirthDto,
                                          Long targetSerialNumber);
    void deleteBirthReportResident(Long reportResidentSerialNumber,
                                   BirthDeathCode birthDeathCode,
                                   Long targetSerialNumber);
    PostDeathDto createBirthReportResident(Long reportResidentSerialNumber,
                                           PostDeathDto postDeathDto);

    PutDeathDto modifyBirthReportResident(Long reportResidentSerialNumber,
                                          PutDeathDto putDeathDto,
                                          Long targetSerialNumber);

    BirthDeathReportResident findBirthReportResidentCertification(Long reportResidentSerialNumber);

    List<Resident> findFamily(Long reportResidentSerialNumber, String  familyRelation);
    List<FamilyRelationship> findAllFamily(Long reportResidentSerialNumber);
    BirthDeathReportResident findDeathReportResidentCertification(Long reportResidentSerialNumber);

    Resident findResident(Long reportResidentSerialNumber);
    Long saveCertificationIssue(Long residentSerialNumber, String certificationIssueCode, LocalDate localDate);
}