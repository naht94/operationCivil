package com.nhnacademy.operationcivil.repository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface HouseholdRepositoryCustom {
    boolean hasFamily(Long residentSerialNumber);
}
