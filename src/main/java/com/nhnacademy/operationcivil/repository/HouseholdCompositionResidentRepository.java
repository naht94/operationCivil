package com.nhnacademy.operationcivil.repository;

import com.nhnacademy.operationcivil.entity.HouseholdCompositionResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HouseholdCompositionResidentRepository extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.Pk>, HouseholdCompositionResidentRepositoryCustom {
    @Query("select h.pk.householdSerialNumber from HouseholdCompositionResident h where h.pk.residentSerialNumber = :residentSerialNumber")
    Long findPk_householdSerialNumberByPk_ResidentSerialNumber(@Param("residentSerialNumber") Long residentSerialNumber);
}
