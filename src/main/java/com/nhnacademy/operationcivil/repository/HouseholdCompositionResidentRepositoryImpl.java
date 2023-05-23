package com.nhnacademy.operationcivil.repository;

import com.nhnacademy.operationcivil.dto.HouseholdCertificationDto;
import com.nhnacademy.operationcivil.dto.QHouseholdCertificationDto;
import com.nhnacademy.operationcivil.entity.HouseholdCompositionResident;
import com.nhnacademy.operationcivil.entity.QHouseholdCompositionResident;
import com.nhnacademy.operationcivil.entity.QResident;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class HouseholdCompositionResidentRepositoryImpl extends QuerydslRepositorySupport implements HouseholdCompositionResidentRepositoryCustom {
    public HouseholdCompositionResidentRepositoryImpl() {
        super(HouseholdCompositionResident.class);
    }

    @Override
    public List<HouseholdCertificationDto> getHouseholdCertification(Long householdSerialNumber) {
        QHouseholdCompositionResident householdCompositionResident = QHouseholdCompositionResident.householdCompositionResident;
        QResident resident = QResident.resident;

        return from(householdCompositionResident)
                .innerJoin(householdCompositionResident.resident, resident)
                .where(householdCompositionResident.pk.householdSerialNumber.eq(householdSerialNumber))
                .select(new QHouseholdCertificationDto(householdCompositionResident.householdRelationshipCode, resident.name, resident.residentRegistrationNumber, householdCompositionResident.reportDate, householdCompositionResident.householdCompositionChangeReasonCode))
                .fetch();
    }

}