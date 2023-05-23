package com.nhnacademy.operationcivil.repository;

import com.nhnacademy.operationcivil.entity.HouseholdMovementAddress;
import com.nhnacademy.operationcivil.entity.QHousehold;
import com.nhnacademy.operationcivil.entity.QHouseholdMovementAddress;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class HouseholdMomentAddressRepositoryImpl extends QuerydslRepositorySupport implements HouseholdMomentAddressRepositoryCustom {
    public HouseholdMomentAddressRepositoryImpl() {
        super(HouseholdMovementAddress.class);
    }

    @Override
    public List<HouseholdMovementAddress> getMovementAddress(Long householdSerialNumber) {
        QHousehold household = QHousehold.household;
        QHouseholdMovementAddress householdMovementAddress = QHouseholdMovementAddress.householdMovementAddress;
        return from(householdMovementAddress)
                .innerJoin(householdMovementAddress.household, household)
                .where(household.householdSerialNumber.eq(householdSerialNumber))
                .fetch();
    }
}
