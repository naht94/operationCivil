package com.nhnacademy.operationcivil.repository;

import com.nhnacademy.operationcivil.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdMomentAddressRepository extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk>, HouseholdMomentAddressRepositoryCustom {
}
