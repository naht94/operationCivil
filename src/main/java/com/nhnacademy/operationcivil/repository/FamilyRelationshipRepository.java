package com.nhnacademy.operationcivil.repository;

import com.nhnacademy.operationcivil.entity.FamilyRelationship;
import com.nhnacademy.operationcivil.entity.Resident;
import com.nhnacademy.operationcivil.enumdata.FamilyRelation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk> {
    @Query("select r " +
            "from FamilyRelationship fr inner join Resident r " +
            "on r.residentSerialNumber = fr.pk.familyResidentSerialNumber " +
            "where fr.pk.baseResidentSerialNumber = :residentSerialNumber " +
            "and fr.familyRelationshipCode = :familyRelation")
    List<Resident> findFamily(@Param("residentSerialNumber") Long residentSerialNumber, @Param("familyRelation") String familyRelation);
    @EntityGraph("familyResident")
    @Query("select fr " +
            "from FamilyRelationship fr inner join Resident r " +
            "on r.residentSerialNumber = fr.pk.familyResidentSerialNumber " +
            "where fr.pk.baseResidentSerialNumber = :residentSerialNumber " +
            "and fr.familyRelationshipCode = :familyRelation")
    List<FamilyRelationship> findAllFamily(@Param("residentSerialNumber") Long residentSerialNumber, @Param("familyRelation") String familyRelation);

}
