/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.dtt.dto.ReportMissingDTO;
import com.dtt.pojo.Registration;
import com.dtt.pojo.ReportMissing;
import com.dtt.repositories.ReportMissingRepository;
import com.dtt.services.ActivityParticipationTypeService;
import com.dtt.services.ActivityService;
import com.dtt.services.RegistrationService;
import com.dtt.services.ReportMissingService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThienTu
 */

@Service
public class ReportMissingServiceImpl implements ReportMissingService{
    @Autowired
    private ReportMissingRepository reportMissingRepository;
    @Autowired
    private ActivityService acService;
    @Autowired
    private ActivityParticipationTypeService acPartTypeService;
    @Autowired
    private RegistrationService registrationService;

    @Override
    public List<ReportMissing> getReportMissings() {
        return this.reportMissingRepository.getReportMissings();
    }

    @Override
    public void confirmReportMissingById(int id) {
        this.reportMissingRepository.confirmReportMissingById(id);
    }

    @Override
    public void rejectReportMissingById(int id) {
        this.reportMissingRepository.rejectReportMissingById(id);
    }

    @Override
    public void save(ReportMissing r) {
        this.reportMissingRepository.save(r);
    }

    @Override
    public ReportMissingDTO convertToDTO(ReportMissing r) {
        ReportMissingDTO dto = new ReportMissingDTO();
        
        Registration registration = this.registrationService.findByStudentIdAndActivityParticipationTypeId(r.getStudentId().getId(), r.getActivityParticipationTypeId().getId());
        
        dto.setId(r.getId());
        if(registration != null){
            if(!r.getChecked()) dto.setStatus("Pending");
            else if(r.getChecked() && !registration.getParticipated()) dto.setStatus("Rejected");
            else if (r.getChecked() && registration.getParticipated()) dto.setStatus("Confirmed");
        } else dto.setStatus("Pending");
        
        dto.setProof(r.getProof());
        dto.setReportDate(r.getReportDate());
        dto.setActivityPartType(this.acPartTypeService.convertToDTO(r.getActivityParticipationTypeId(), true, 0));
        
        return dto;
    }

    @Override
    public List<ReportMissingDTO> getStudentReportMissings(int studentId, Map<String, String> params) {
        
        return this.reportMissingRepository.getStudentReportMissings(studentId, params).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ReportMissing findByStudentIdAndActivityParticipationTypeId(int studentId, int activityParticipationTypeId) {
        return this.reportMissingRepository.findByStudentIdAndActivityParticipationTypeId(studentId, activityParticipationTypeId);
    }
    
    
}
