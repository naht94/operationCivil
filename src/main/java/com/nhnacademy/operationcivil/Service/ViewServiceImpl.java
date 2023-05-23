package com.nhnacademy.operationcivil.Service;

import com.nhnacademy.operationcivil.dto.ResidentDto;
import com.nhnacademy.operationcivil.repository.HouseholdRepository;
import com.nhnacademy.operationcivil.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewServiceImpl implements ViewService {
    private final ResidentRepository residentRepository;
    @Override
    public Page<ResidentDto> getPage(Pageable pageable) {
        return residentRepository.getAllBy(pageable);
    }
}
