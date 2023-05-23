package com.nhnacademy.operationcivil.Service;

import com.nhnacademy.operationcivil.dto.HouseholdCertificationDto;
import com.nhnacademy.operationcivil.dto.PostHouseholdDto;
import com.nhnacademy.operationcivil.dto.PostHouseholdMovementAddressDto;
import com.nhnacademy.operationcivil.dto.PutHouseholdMovementAddressDto;
import com.nhnacademy.operationcivil.entity.Household;
import com.nhnacademy.operationcivil.entity.HouseholdMovementAddress;
import com.nhnacademy.operationcivil.entity.Resident;
import com.nhnacademy.operationcivil.exception.HasNoTarget;
import com.nhnacademy.operationcivil.repository.HouseholdCompositionResidentRepository;
import com.nhnacademy.operationcivil.repository.HouseholdMomentAddressRepository;
import com.nhnacademy.operationcivil.repository.HouseholdRepository;
import com.nhnacademy.operationcivil.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HouseholdServiceImpl implements HouseholdService {
    private final HouseholdRepository householdRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
    private final HouseholdMomentAddressRepository householdMovementAddressRepository;
    private final ResidentRepository residentRepository;

    @Transactional
    @Override
    public PostHouseholdDto createHousehold(PostHouseholdDto postHouseholdDto) {
        Household household = new Household();

        household.setHouseholdSerialNumber(postHouseholdDto.getHouseholdSerialNumber());
        household.setHouseholdCompositionDate(postHouseholdDto.getHouseholdCompositionDate());
        Optional<Resident> target = residentRepository.findById(postHouseholdDto.getResidentSerialNumber());
        if (target.isPresent()) {
            Resident resident = target.get();

            household.setResident(resident);
            household.setHouseholdCompositionReasonCode(postHouseholdDto.getHouseholdCompositionReasonCode());
            household.setCurrentHouseMovementAddress(postHouseholdDto.getCurrentHouseMovementAddress());

            householdRepository.save(household);

            return postHouseholdDto;
        }
        throw new HasNoTarget(postHouseholdDto.getResidentSerialNumber());
    }

    @Transactional
    @Override
    public void deleteHousehold(Long householdSerialNumber) {
        householdRepository.deleteById(householdSerialNumber);
    }

    @Transactional
    @Override
    public PostHouseholdMovementAddressDto createHouseholdMomentAddressDto(Long householdSerialNumber, PostHouseholdMovementAddressDto postHouseholdMomentAddressDto) {
        HouseholdMovementAddress householdMovementAddress = new HouseholdMovementAddress();
        Optional<Household> target = householdRepository.findById(householdSerialNumber);
        if (target.isPresent()) {
            Household household = target.get();

            householdMovementAddress.setPk(new HouseholdMovementAddress.Pk(householdSerialNumber, postHouseholdMomentAddressDto.getHouseMovementReportDate()));
            householdMovementAddress.setHousehold(household);
            householdMovementAddress.setLastAddressYn(postHouseholdMomentAddressDto.getLastAddressYn());
            householdMovementAddress.setHouseMovementAddress(postHouseholdMomentAddressDto.getHouseMovementAddress());

            householdMovementAddressRepository.save(householdMovementAddress);
            return postHouseholdMomentAddressDto;
        }

        throw new HasNoTarget(new HouseholdMovementAddress.Pk(householdSerialNumber, postHouseholdMomentAddressDto.getHouseMovementReportDate()));
    }

    @Transactional
    @Override
    public PutHouseholdMovementAddressDto modifyHouseholdMomentAddressDto(Long householdSerialNumber, PutHouseholdMovementAddressDto putHouseholdMomentAddressDto, LocalDate houseMovementReportDate) {
        Optional<HouseholdMovementAddress> target = householdMovementAddressRepository.findById(new HouseholdMovementAddress.Pk(householdSerialNumber, houseMovementReportDate));
        if (target.isPresent()) {
            HouseholdMovementAddress householdMovementAddress = target.get();

            householdMovementAddress.setLastAddressYn(putHouseholdMomentAddressDto.getLastAddressYn());
            householdMovementAddress.setHouseMovementAddress(putHouseholdMomentAddressDto.getHouseMovementAddress());

            return putHouseholdMomentAddressDto;
        }
        throw new HasNoTarget(new HouseholdMovementAddress.Pk(householdSerialNumber, houseMovementReportDate));
    }
    @Transactional
    @Override
    public void deleteHouseholdMovementAddressDto(Long householdSerialNumber, LocalDate houseMovementReportDate) {
        if (!householdMovementAddressRepository.existsById(new HouseholdMovementAddress.Pk(householdSerialNumber, houseMovementReportDate))) {
            throw new HasNoTarget(new HouseholdMovementAddress.Pk(householdSerialNumber, houseMovementReportDate));
        }
        householdMovementAddressRepository.deleteById(new HouseholdMovementAddress.Pk(householdSerialNumber, houseMovementReportDate));
    }

    @Override
    public List<HouseholdCertificationDto> getCertification(Long householdSerialNumber) {
        return householdCompositionResidentRepository.getHouseholdCertification(householdSerialNumber);
    }

    @Override
    public Long getHouseholdSerialNumber(Long residentSerialNumber) {
        return householdCompositionResidentRepository.findPk_householdSerialNumberByPk_ResidentSerialNumber(residentSerialNumber);
    }

    @Override
    public List<HouseholdMovementAddress> getMovementAddress(Long householdSerialNumber) {
        List<HouseholdMovementAddress> movementAddress = householdMovementAddressRepository.getMovementAddress(householdSerialNumber);
        Collections.reverse(movementAddress);
        return movementAddress;
    }

    @Override
    public Household getHousehold(Long householdSerialNumber) {
        Optional<Household> byId = householdRepository.findById(householdSerialNumber);
        if (!byId.isPresent()) {
            throw new HasNoTarget(householdSerialNumber);
        }
        return byId.get();
    }
}
