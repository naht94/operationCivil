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
import com.nhnacademy.operationcivil.entity.CertificateIssue;
import com.nhnacademy.operationcivil.entity.FamilyRelationship;
import com.nhnacademy.operationcivil.entity.Resident;
import com.nhnacademy.operationcivil.enumdata.BirthDeathCode;
import com.nhnacademy.operationcivil.enumdata.FamilyRelation;
import com.nhnacademy.operationcivil.exception.HasFamily;
import com.nhnacademy.operationcivil.exception.HasNoTarget;
import com.nhnacademy.operationcivil.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.operationcivil.repository.CertificateIssueRepository;
import com.nhnacademy.operationcivil.repository.FamilyRelationshipRepository;
import com.nhnacademy.operationcivil.repository.HouseholdRepository;
import com.nhnacademy.operationcivil.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;
    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;
    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final HouseholdRepository householdRepository;
    private final CertificateIssueRepository certificateIssueRepository;

    @Override
    public PostResidentDto createResident(PostResidentDto postResidentDto) {
        Resident resident = new Resident();

        resident.setResidentSerialNumber(postResidentDto.getResidentSerialNumber());
        resident.setName(postResidentDto.getName());
        resident.setResidentRegistrationNumber(postResidentDto.getResidentRegistrationNumber());
        resident.setGenderCode(postResidentDto.getGenderCode());
        resident.setBirthDate(postResidentDto.getBirthDate());
        resident.setBirthPlaceCode(postResidentDto.getBirthPlaceCode());
        resident.setRegistrationBaseAddress(postResidentDto.getRegistrationBaseAddress());

        residentRepository.save(resident);
        return postResidentDto;
    }

    @Override
    public PutResidentDto modifyResident(Long residentSerialNumber, PutResidentDto putResidentDto) {
        Optional<Resident> target = residentRepository.findById(residentSerialNumber);
        if (target.isPresent()) {
            Resident resident = target.get();
            resident.setResidentSerialNumber(residentSerialNumber);
            resident.setName(putResidentDto.getName());
            resident.setResidentRegistrationNumber(putResidentDto.getResidentRegistrationNumber());
            resident.setGenderCode(putResidentDto.getGenderCode());
            resident.setBirthDate(putResidentDto.getBirthDate());
            resident.setBirthPlaceCode(putResidentDto.getBirthPlaceCode());
            resident.setRegistrationBaseAddress(putResidentDto.getRegistrationBaseAddress());
            residentRepository.save(resident);
            return putResidentDto;
        }
        throw new HasNoTarget(residentSerialNumber);
    }

    @Override
    public void deleteResident(Long residentSerialNumber) {
        if (!residentRepository.existsById(residentSerialNumber)) {
            throw new HasNoTarget(residentSerialNumber);
        }
        if (householdRepository.hasFamily(residentSerialNumber)) {
            throw new HasFamily();
        }
        residentRepository.deleteById(residentSerialNumber);
    }

    @Transactional
    @Override
    public PostRelationshipDto createFamilyRelationship(Long baseResidentSerialNumber,
                                                        Long familyResidentSerialNumber,
                                                        String familyRelationshipCode) {
        FamilyRelationship familyRelationship = new FamilyRelationship();

        familyRelationship.setPk(new FamilyRelationship.Pk(baseResidentSerialNumber, familyResidentSerialNumber));
        Optional<Resident> baseTarget = residentRepository.findById(baseResidentSerialNumber);
        Optional<Resident> familyTarget = residentRepository.findById(familyResidentSerialNumber);
        if (!baseTarget.isPresent() || !familyTarget.isPresent()) {
            throw new HasNoTarget(new FamilyRelationship.Pk(baseResidentSerialNumber, familyResidentSerialNumber));
        }
        familyRelationship.setBaseResident(baseTarget.get());
        familyRelationship.setFamilyResident(familyTarget.get());
        familyRelationship.setFamilyRelationshipCode(FamilyRelation.valueOf(familyRelationshipCode).getKorean());

        familyRelationshipRepository.save(familyRelationship);

        PostRelationshipDto postRelationshipDto = new PostRelationshipDto();

        postRelationshipDto.setFamilySerialNumber(familyRelationship.getPk().getBaseResidentSerialNumber());
        postRelationshipDto.setRelationShip(familyRelationship.getFamilyRelationshipCode());

        return postRelationshipDto;
    }
    @Transactional
    @Override
    public PutRelationshipDto modifyFamilyRelationship(Long baseResidentSerialNumber,
                                                       Long familyResidentSerialNumber,
                                                       String familyRelationshipCode) {
        Optional<FamilyRelationship> target = familyRelationshipRepository.findById(new FamilyRelationship.Pk(baseResidentSerialNumber, familyResidentSerialNumber));
        if (target.isPresent()) {
            FamilyRelationship familyRelationship = target.get();
            familyRelationship.setFamilyRelationshipCode(FamilyRelation.valueOf(familyRelationshipCode).getKorean());
            familyRelationshipRepository.save(familyRelationship);

            PutRelationshipDto putRelationshipDto = new PutRelationshipDto();

            putRelationshipDto.setRelationShip(familyRelationship.getFamilyRelationshipCode());
            return putRelationshipDto;
        }

        throw new HasNoTarget(new FamilyRelationship.Pk(baseResidentSerialNumber, familyResidentSerialNumber));
    }

    @Transactional
    @Override
    public void deleteFamilyRelationship(Long baseResidentSerialNumber, Long familyResidentSerialNumber) {
        if (!familyRelationshipRepository.existsById(new FamilyRelationship.Pk(baseResidentSerialNumber, familyResidentSerialNumber))) {
            throw new HasNoTarget(new FamilyRelationship.Pk(baseResidentSerialNumber, familyResidentSerialNumber));
        }
        familyRelationshipRepository.deleteById(new FamilyRelationship.Pk(baseResidentSerialNumber, familyResidentSerialNumber));
    }
    @Transactional
    @Override
    public PostBirthDto createBirthReportResident(Long reportResidentSerialNumber, PostBirthDto postBirthDto) {
        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();

        birthDeathReportResident.setPk(new BirthDeathReportResident.Pk(reportResidentSerialNumber, postBirthDto.getBirthDeathTypeCode(), postBirthDto.getResidentSerialNumber()));
        Optional<Resident> isResident = residentRepository.findById(postBirthDto.getResidentSerialNumber());
        Optional<Resident> isReportResident = residentRepository.findById(reportResidentSerialNumber);
        if (!isResident.isPresent() || !isReportResident.isPresent()) {
            throw new HasNoTarget(new BirthDeathReportResident.Pk(reportResidentSerialNumber, postBirthDto.getBirthDeathTypeCode(), postBirthDto.getResidentSerialNumber()));
        }
        Resident resident = isResident.get();
        Resident reportResident = isReportResident.get();

        birthDeathReportResident.setResident(resident);
        birthDeathReportResident.setReportResident(reportResident);
        birthDeathReportResident.setBirthDeathReportDate(postBirthDto.getBirthDeathReportDate());
        birthDeathReportResident.setEmailAddress(postBirthDto.getEmailAddress());
        birthDeathReportResident.setPhoneNumber(postBirthDto.getPhoneNumber());
        birthDeathReportResident.setBirthReportQualificationsCode(postBirthDto.getBirthReportQualificationsCode());

        birthDeathReportResidentRepository.save(birthDeathReportResident);

        return postBirthDto;
    }
    @Transactional
    @Override
    public PutBirthDto modifyBirthReportResident(Long reportResidentSerialNumber, PutBirthDto putBirthDto, Long targetSerialNumber) {
        Optional<BirthDeathReportResident> target = birthDeathReportResidentRepository.findById(new BirthDeathReportResident.Pk(reportResidentSerialNumber, BirthDeathCode.출생.name(), targetSerialNumber));

        if (target.isPresent()) {
            BirthDeathReportResident birthDeathReportResident = target.get();
            birthDeathReportResident.setBirthDeathReportDate(putBirthDto.getBirthDeathReportDate());
            birthDeathReportResident.setEmailAddress(putBirthDto.getEmailAddress());
            birthDeathReportResident.setPhoneNumber(putBirthDto.getPhoneNumber());
            birthDeathReportResident.setBirthReportQualificationsCode(putBirthDto.getBirthReportQualificationsCode());

            birthDeathReportResidentRepository.save(birthDeathReportResident);

            return putBirthDto;
        }
        throw new HasNoTarget(new BirthDeathReportResident.Pk(reportResidentSerialNumber, BirthDeathCode.출생.name(), targetSerialNumber));
    }
    @Transactional
    @Override
    public void deleteBirthReportResident(Long reportResidentSerialNumber, BirthDeathCode birthDeathCode, Long targetSerialNumber) {
        if (!birthDeathReportResidentRepository.existsById(new BirthDeathReportResident.Pk(reportResidentSerialNumber, birthDeathCode.name(), targetSerialNumber))) {
            throw new HasNoTarget(new BirthDeathReportResident.Pk(reportResidentSerialNumber, birthDeathCode.name(), targetSerialNumber));
        }
        birthDeathReportResidentRepository.deleteById(new BirthDeathReportResident.Pk(reportResidentSerialNumber, birthDeathCode.name(), targetSerialNumber));
    }
    @Transactional
    @Override
    public PostDeathDto createBirthReportResident(Long reportResidentSerialNumber, PostDeathDto postDeathDto) {
        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();

        birthDeathReportResident.setPk(new BirthDeathReportResident.Pk(reportResidentSerialNumber, postDeathDto.getBirthDeathTypeCode(), postDeathDto.getResidentSerialNumber()));
        Optional<Resident> isResident = residentRepository.findById(postDeathDto.getResidentSerialNumber());
        Optional<Resident> isReportResident = residentRepository.findById(reportResidentSerialNumber);
        if (!isResident.isPresent() || !isReportResident.isPresent()) {
            throw new HasNoTarget(new BirthDeathReportResident.Pk(reportResidentSerialNumber, postDeathDto.getBirthDeathTypeCode(), postDeathDto.getResidentSerialNumber()));
        }
        Resident resident = isResident.get();
        Resident reportResident = isReportResident.get();

        birthDeathReportResident.setResident(resident);
        birthDeathReportResident.setReportResident(reportResident);
        birthDeathReportResident.setBirthDeathReportDate(postDeathDto.getBirthDeathReportDate());
        birthDeathReportResident.setEmailAddress(postDeathDto.getEmailAddress());
        birthDeathReportResident.setPhoneNumber(postDeathDto.getPhoneNumber());
        birthDeathReportResident.setBirthReportQualificationsCode(postDeathDto.getDeathReportQualificationsCode());

        birthDeathReportResidentRepository.save(birthDeathReportResident);

        return postDeathDto;
    }
    @Transactional
    @Override
    public PutDeathDto modifyBirthReportResident(Long reportResidentSerialNumber, PutDeathDto putDeathDto, Long targetSerialNumber) {
        Optional<BirthDeathReportResident> target = birthDeathReportResidentRepository.findById(new BirthDeathReportResident.Pk(reportResidentSerialNumber, BirthDeathCode.사망.name(), targetSerialNumber));

        if (target.isPresent()) {
            BirthDeathReportResident birthDeathReportResident = target.get();

            birthDeathReportResident.setBirthDeathReportDate(putDeathDto.getBirthDeathReportDate());
            birthDeathReportResident.setEmailAddress(putDeathDto.getEmailAddress());
            birthDeathReportResident.setPhoneNumber(putDeathDto.getPhoneNumber());
            birthDeathReportResident.setBirthReportQualificationsCode(putDeathDto.getDeathReportQualificationsCode());

            birthDeathReportResidentRepository.save(birthDeathReportResident);

            return putDeathDto;
        }
        throw new HasNoTarget(new BirthDeathReportResident.Pk(reportResidentSerialNumber, BirthDeathCode.사망.name(), targetSerialNumber));
    }
    @Transactional
    @Override
    public BirthDeathReportResident findBirthReportResidentCertification(Long reportResidentSerialNumber) {
        return birthDeathReportResidentRepository.findBirthDeathReportResidentByPk_ResidentSerialNumber(reportResidentSerialNumber);
    }
    @Transactional
    @Override
    public List<Resident> findFamily(Long reportResidentSerialNumber, String familyRelation) {
        return familyRelationshipRepository.findFamily(reportResidentSerialNumber, familyRelation);
    }
    @Transactional
    @Override
    public List<FamilyRelationship> findAllFamily(Long reportResidentSerialNumber) {
        List<FamilyRelationship> family = new ArrayList<>(familyRelationshipRepository.findAllFamily(reportResidentSerialNumber, FamilyRelation.father.getKorean()));
        family.addAll(familyRelationshipRepository.findAllFamily(reportResidentSerialNumber, FamilyRelation.mother.getKorean()));
        family.addAll(familyRelationshipRepository.findAllFamily(reportResidentSerialNumber, FamilyRelation.spouse.getKorean()));
        family.addAll(familyRelationshipRepository.findAllFamily(reportResidentSerialNumber, FamilyRelation.child.getKorean()));
        return family;
    }
    @Transactional
    @Override
    public BirthDeathReportResident findDeathReportResidentCertification(Long reportResidentSerialNumber) {
        return birthDeathReportResidentRepository.findBirthDeathReportResidentByPk_ResidentSerialNumber(reportResidentSerialNumber);
    }
    @Transactional
    @Override
    public Resident findResident(Long residentSerialNumber) {
        Optional<Resident> byId = residentRepository.findById(residentSerialNumber);
        return byId.get();
    }
    @Transactional
    @Override
    public Long saveCertificationIssue(Long residentSerialNumber, String certificationIssueCode, LocalDate localDate) {
        CertificateIssue certificateIssue = new CertificateIssue();
        certificateIssue.setCertificateIssueDate(localDate);
        certificateIssue.setCertificate_type_code(certificationIssueCode);
        certificateIssue.setResident(residentRepository.findById(residentSerialNumber).get());
        certificateIssueRepository.save(certificateIssue);
        return certificateIssue.getCertificateConfirmationNumber();
    }
}
