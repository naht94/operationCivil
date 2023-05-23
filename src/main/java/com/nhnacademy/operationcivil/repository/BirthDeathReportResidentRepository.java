package com.nhnacademy.operationcivil.repository;

import com.nhnacademy.operationcivil.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthDeathReportResidentRepository extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk> {
    BirthDeathReportResident findBirthDeathReportResidentByPk_ResidentSerialNumber(Long residentSerialNumber);
}
