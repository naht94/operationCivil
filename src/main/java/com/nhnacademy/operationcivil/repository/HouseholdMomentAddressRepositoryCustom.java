package com.nhnacademy.operationcivil.repository;

import com.nhnacademy.operationcivil.entity.HouseholdMovementAddress;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface HouseholdMomentAddressRepositoryCustom {
    List<HouseholdMovementAddress> getMovementAddress(Long householdSerialNumber);

}
