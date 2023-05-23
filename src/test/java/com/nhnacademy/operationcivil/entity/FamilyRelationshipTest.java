package com.nhnacademy.operationcivil.entity;

import com.nhnacademy.operationcivil.config.RootConfig;
import com.nhnacademy.operationcivil.config.WebConfig;
import com.nhnacademy.operationcivil.repository.FamilyRelationshipRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class FamilyRelationshipTest {
    @Autowired
    FamilyRelationshipRepository familyRelationshipRepository;
    @Test
    void test() {
        Optional<FamilyRelationship> familyRelationship = familyRelationshipRepository.findById(new FamilyRelationship.Pk(1L, 2L));
        familyRelationship.ifPresent(relationship -> Assertions.assertThat(relationship.getFamilyRelationshipCode()).isEqualTo("자녀"));
    }

}