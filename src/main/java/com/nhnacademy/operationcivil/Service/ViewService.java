package com.nhnacademy.operationcivil.Service;


import com.nhnacademy.operationcivil.dto.ResidentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ViewService {
    Page<ResidentDto> getPage(Pageable pageable);
}
