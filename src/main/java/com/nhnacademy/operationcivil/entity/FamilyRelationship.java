package com.nhnacademy.operationcivil.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
@NamedEntityGraph(name = "familyResident", attributeNodes = {
                @NamedAttributeNode("familyResident")}
)
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "family_relationship")
@AllArgsConstructor
public class FamilyRelationship {
    @EmbeddedId
    private Pk pk;

    @Embeddable
    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Pk implements Serializable {
        @Column(name = "base_resident_serial_number")
        private Long baseResidentSerialNumber;
        @Column(name = "family_resident_serial_number")
        private Long familyResidentSerialNumber;

        public Pk(Long baseResidentSerialNumber, Long familyResidentSerialNumber) {
            this.baseResidentSerialNumber = baseResidentSerialNumber;
            this.familyResidentSerialNumber = familyResidentSerialNumber;
        }
    }

    @MapsId("baseResidentSerialNumber")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "base_resident_serial_number")
    private Resident baseResident;
    @MapsId("familyResidentSerialNumber")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "family_resident_serial_number")
    private Resident familyResident;
    @Column(name = "family_relationship_code")
    private String familyRelationshipCode;
}
