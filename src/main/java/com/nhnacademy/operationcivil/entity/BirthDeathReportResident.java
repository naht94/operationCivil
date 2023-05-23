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
import javax.persistence.NamedEntityGraphs;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
@NamedEntityGraphs(
        @NamedEntityGraph(name = "birthCertificationDtoWithResidentDtoAndReportResidentDto", attributeNodes = {
                @NamedAttributeNode("resident"),
                @NamedAttributeNode("reportResident")
        })
)
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "birth_death_report_resident")
public class BirthDeathReportResident {

    @EmbeddedId
    private Pk pk;

    @NoArgsConstructor
    @EqualsAndHashCode
    @AllArgsConstructor
    @Embeddable
    @Getter
    public static class Pk implements Serializable {
        @Column(name = "report_resident_serial_number")
        private Long reportResidentSerialNumber;
        @Column(name = "birth_death_type_code")
        private String birthDeathTypeCode;
        @Column(name = "resident_serial_number")
        private Long residentSerialNumber;
    }
    @MapsId("residentSerialNumber")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;
    @MapsId("reportResidentSerialNumber")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "report_resident_serial_number")
    private Resident reportResident;
    @Column(name = "birth_death_report_date")
    private LocalDate birthDeathReportDate;
    @Column(name = "birth_report_qualifications_code")
    private String birthReportQualificationsCode;
    @Column(name = "death_report_qualifications_code")
    private String deathReportQualificationsCode;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "phone_number")
    private String phoneNumber;
}
