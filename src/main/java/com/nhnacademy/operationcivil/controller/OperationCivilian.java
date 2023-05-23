package com.nhnacademy.operationcivil.controller;

import com.nhnacademy.operationcivil.Service.HouseholdService;
import com.nhnacademy.operationcivil.Service.ResidentService;
import com.nhnacademy.operationcivil.Service.ViewService;
import com.nhnacademy.operationcivil.dto.HouseholdCertificationDto;
import com.nhnacademy.operationcivil.dto.ResidentDto;
import com.nhnacademy.operationcivil.entity.BirthDeathReportResident;
import com.nhnacademy.operationcivil.entity.FamilyRelationship;
import com.nhnacademy.operationcivil.entity.Household;
import com.nhnacademy.operationcivil.entity.HouseholdMovementAddress;
import com.nhnacademy.operationcivil.entity.Resident;
import com.nhnacademy.operationcivil.enumdata.CertificationIssueCode;
import com.nhnacademy.operationcivil.enumdata.FamilyRelation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping
public class OperationCivilian {
    private final ViewService viewService;
    private final ResidentService residentService;
    private final HouseholdService householdService;

    public OperationCivilian(ViewService viewService, ResidentService residentService, HouseholdService householdService) {
        this.viewService = viewService;
        this.residentService = residentService;
        this.householdService  = householdService;

    }

    @GetMapping({"/index", ""})
    public String civilianBoard(Model model, @PageableDefault(size = 5, sort = "residentSerialNumber", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<ResidentDto> residents = viewService.getPage(pageable);
        model.addAttribute("residents", residents);
        model.addAttribute("page" ,residents.getNumber());
        return "index";
    }

    @PostMapping("/family/civilian")
    public String civilianFamily(@RequestParam(name = "id") String id, Model model){
        Resident self = residentService.findResident(Long.parseLong(id));
        List<FamilyRelationship> family = residentService.findAllFamily(Long.parseLong(id));
        LocalDate localDate = LocalDate.now();
        Long certificateConfirmationNumber = residentService.saveCertificationIssue(Long.parseLong(id), CertificationIssueCode.가족관계증명서.name(), localDate);
        model.addAttribute("self", self);
        model.addAttribute("family", family);
        model.addAttribute("certificateConfirmationNumber", certificateConfirmationNumber);
        model.addAttribute("localDate", localDate);
        return "family_relation_certificate";
    }
    @PostMapping ("/registration/civilian")
    public String civilianRegistration(@RequestParam(name = "id") String id, Model model){
        Long householdSerialNumber = householdService.getHouseholdSerialNumber(Long.parseLong(id));
        List<HouseholdCertificationDto> certification = householdService.getCertification(householdSerialNumber);
        List<HouseholdMovementAddress> movementAddress = householdService.getMovementAddress(householdSerialNumber);
        Household household = householdService.getHousehold(householdSerialNumber);
        LocalDate localDate = LocalDate.now();
        Long certificateConfirmationNumber = residentService.saveCertificationIssue(Long.parseLong(id), CertificationIssueCode.주민등록등본.name(), localDate);

        model.addAttribute("certification", certification);
        model.addAttribute("movementAddress", movementAddress);
        model.addAttribute("household", household);
        model.addAttribute("certificateConfirmationNumber", certificateConfirmationNumber);
        model.addAttribute("localDate", localDate);
        return "registration";
    }
    @PostMapping ("/birth/civilian")
    public String civilianBirth(@RequestParam(name = "id") String id, Model model){
        BirthDeathReportResident birthDeathReportResident = residentService.findBirthReportResidentCertification(Long.parseLong(id));
        List<Resident> father = residentService.findFamily(Long.parseLong(id), FamilyRelation.father.getKorean());
        List<Resident> mother = residentService.findFamily(Long.parseLong(id), FamilyRelation.mother.getKorean());
        model.addAttribute("father", father.get(0));
        model.addAttribute("mother", mother.get(0));
        model.addAttribute("birthDeathReportResident", birthDeathReportResident);
        return "birth_certification";
    }
    @PostMapping("/death/civilian")
    public String civilianDeath(@RequestParam(name = "id") String id, Model model){
        BirthDeathReportResident birthDeathReportResident = residentService.findBirthReportResidentCertification(Long.parseLong(id));
        model.addAttribute("birthDeathReportResident", birthDeathReportResident);

        return "death_certification";
    }
    @DeleteMapping ("/delete/civilian")
    public String afterDeleteCivilianBoard(@RequestParam(name = "id") String id){
        residentService.deleteResident(Long.parseLong(id));
        return "redirect:/index";
    }
}