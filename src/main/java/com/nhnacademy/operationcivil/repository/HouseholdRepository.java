package com.nhnacademy.operationcivil.repository;

import com.nhnacademy.operationcivil.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Long>, HouseholdRepositoryCustom {
}
