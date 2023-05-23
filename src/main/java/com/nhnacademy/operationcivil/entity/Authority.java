package com.nhnacademy.operationcivil.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "Authorities")
public class Authority {
    @Id
    @Column(name = "member_id")
    private String memberId;
    private String authority;
    @MapsId
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}