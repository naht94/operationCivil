package com.nhnacademy.operationcivil.entity;

import com.nhnacademy.operationcivil.config.RootConfig;
import com.nhnacademy.operationcivil.config.WebConfig;
import com.nhnacademy.operationcivil.repository.ResidentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class ResidentTest {
    @Autowired
    ResidentRepository residentRepository;

    @Test
    void test() {
        Resident resident = residentRepository.findById(1L).get();
        Assertions.assertThat(resident.getResidentSerialNumber()).isEqualTo(1L);
    }

}