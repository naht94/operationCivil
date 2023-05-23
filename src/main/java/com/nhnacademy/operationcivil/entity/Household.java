package com.nhnacademy.operationcivil.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;
@NamedEntityGraph(attributeNodes = {
        @NamedAttributeNode("resident")
})
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "household")
public class Household {
    @Id
    @Column(name = "household_serial_number")
    private Long householdSerialNumber;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "household_resident_serial_number", referencedColumnName = "resident_serial_number")
    private Resident resident;
    @MapsId("householdSerialNumber")
    @OneToMany(mappedBy = "household")
    private List<HouseholdCompositionResident> householdCompositionResidents;
    @MapsId("householdSerialNumber")
    @OneToMany(mappedBy = "pk.householdSerialNumber")
    private List<HouseholdMovementAddress> householdMovementAddresses;

    @Column(name = "household_composition_date")
    private LocalDate householdCompositionDate;

    @Column(name = "household_composition_reason_code")
    private String householdCompositionReasonCode;

    @Column(name = "current_house_movement_address")
    private String currentHouseMovementAddress;
}
