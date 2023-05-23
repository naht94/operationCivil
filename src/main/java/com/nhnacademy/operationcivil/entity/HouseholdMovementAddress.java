package com.nhnacademy.operationcivil.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "household_movement_address")
public class HouseholdMovementAddress {
    @EmbeddedId
    private Pk pk;
    @MapsId("householdSerialNumber")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "household_serial_number")
    private Household household;
    @Embeddable
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pk implements Serializable {
        @Column(name = "household_serial_number")
        private Long householdSerialNumber;
        @Column(name = "house_movement_report_date")
        private LocalDate houseMovementReportDate;
    }

    @Column(name = "house_movement_address")
    private String houseMovementAddress;
    @Column(name = "last_address_yn")
    @ColumnDefault("Y")
    private String lastAddressYn;
}
