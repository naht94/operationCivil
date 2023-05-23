package com.nhnacademy.operationcivil.repository;

import com.nhnacademy.operationcivil.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
