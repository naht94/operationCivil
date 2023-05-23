package com.nhnacademy.operationcivil.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
@NamedEntityGraphs(
        @NamedEntityGraph(name = "residentWithBaseResidentsAndReportFamilyResidents", attributeNodes = {
                @NamedAttributeNode("baseResidents"),
                @NamedAttributeNode("familyResidents")
        })
)
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "resident")
public class Resident {
    @Id
    @Column(name = "resident_serial_number")
    private Long residentSerialNumber;
    @MapsId("residentSerialNumber")
    @OneToMany(mappedBy = "resident")
    private List<BirthDeathReportResident> residents;
    @MapsId("residentSerialNumber")
    @OneToMany(mappedBy = "reportResident")
    private List<BirthDeathReportResident> reportResidents;
    @MapsId("residentSerialNumber")
    @OneToMany(mappedBy = "baseResident")
    private List<FamilyRelationship> baseResidents;
    @MapsId("residentSerialNumber")
    @OneToMany(mappedBy = "familyResident")
    private List<FamilyRelationship> familyResidents;
    @MapsId("residentSerialNumber")
    @OneToMany(mappedBy = "resident")
    private List<Household> households;
    @MapsId("residentSerialNumber")
    @OneToMany(mappedBy = "resident")
    private List<HouseholdCompositionResident> householdCompositionResidents;
    @MapsId("residentSerialNumber")
    @OneToMany(mappedBy = "resident")
    private List<CertificateIssue> certificateIssues;
    private String name;
    @Column(name = "resident_registration_number")
    private String residentRegistrationNumber;
    @Column(name = "gender_code")
    private String genderCode;
    @Column(name = "birth_date")
    private LocalDateTime birthDate;
    @Column(name = "birth_place_code")
    private String birthPlaceCode;
    @Column(name = "registration_base_address")
    private String registrationBaseAddress;
    @Column(name = "death_date")
    private LocalDateTime deathDate;
    @Column(name = "death_place_code")
    private String deathPlaceCode;
    @Column(name = "death_place_address")
    private String deathPlaceAddress;
}
