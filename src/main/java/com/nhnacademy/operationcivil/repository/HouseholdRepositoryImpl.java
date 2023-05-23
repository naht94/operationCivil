package com.nhnacademy.operationcivil.repository;

import com.nhnacademy.operationcivil.entity.Household;
import com.nhnacademy.operationcivil.entity.QFamilyRelationship;
import com.nhnacademy.operationcivil.entity.QHousehold;
import com.nhnacademy.operationcivil.entity.QResident;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
@Slf4j
public class HouseholdRepositoryImpl extends QuerydslRepositorySupport implements HouseholdRepositoryCustom{
    public HouseholdRepositoryImpl() {
        super(Household.class);
    }

    @Override
    public boolean hasFamily(Long residentSerialNumber) {
        QHousehold household = QHousehold.household;
        QResident resident = QResident.resident;
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;

        Long count = from(resident)
                .innerJoin(resident.households, household)
                .innerJoin(resident.baseResidents, familyRelationship)
                .where(resident.residentSerialNumber.eq(residentSerialNumber))
                .fetchCount();

        log.error("count: {}", count);
        return count != 0;
    }
}
