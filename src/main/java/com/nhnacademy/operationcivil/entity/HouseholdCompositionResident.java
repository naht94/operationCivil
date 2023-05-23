package com.nhnacademy.operationcivil.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
@NamedEntityGraph( attributeNodes = {
        @NamedAttributeNode("resident")
})
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "household_composition_resident")
public class HouseholdCompositionResident {
    @EmbeddedId
    private Pk pk;

    @MapsId("householdSerialNumber")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "household_serial_number")
    private Household household;
    @MapsId("residentSerialNumber")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;

    @Embeddable
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pk implements Serializable {
        @Column(name = "household_serial_number")
        private Long householdSerialNumber;
        @Column(name = "resident_serial_number")
        private Long residentSerialNumber;
    }

    @Column(name = "report_date")
    private LocalDate reportDate;
    @Column(name = "household_relationship_code")
    private String householdRelationshipCode;

    @Column(name = "household_composition_change_reason_code")
    private String householdCompositionChangeReasonCode;
}
