package com.nhnacademy.operationcivil.repository;

import com.nhnacademy.operationcivil.dto.ResidentDto;
import com.nhnacademy.operationcivil.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
    Page<ResidentDto> getAllBy(Pageable pageable);
}
