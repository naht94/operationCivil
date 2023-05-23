package com.nhnacademy.operationcivil.entity;

import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "certificate_issue")
public class CertificateIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_confirmation_number")
    private Long certificateConfirmationNumber;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "resident_serial_number")
    private Resident resident;
    @Column(name = "certificate_type_code")
    private String certificate_type_code;
    @Column(name = "certificate_issue_date")
    private LocalDate certificateIssueDate;
}
