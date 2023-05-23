package com.nhnacademy.operationcivil.repository;

import com.nhnacademy.operationcivil.dto.HouseholdCertificationDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface HouseholdCompositionResidentRepositoryCustom {
    List<HouseholdCertificationDto> getHouseholdCertification(Long householdSerialNumber);
}
