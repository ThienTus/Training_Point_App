/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.dtt.dto.ActivityParticipationTypeDTO;
import com.dtt.pojo.Activity;
import com.dtt.pojo.ActivityParticipationType;
import com.dtt.pojo.Registration;
import com.dtt.repositories.ActivityParticipationTypeRepository;
import com.dtt.services.ActivityParticipationTypeService;
import com.dtt.services.ActivityService;
import com.dtt.services.RegistrationService;
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
public class ActivityParticipationTypeServiceImpl implements ActivityParticipationTypeService {
    @Autowired
    private ActivityParticipationTypeRepository activityParticipationTypeRepo;
    @Autowired
    private ActivityService acService;
    
    @Autowired
    private RegistrationService registrationService;
    
    @Override
    public List<ActivityParticipationType> getActivityParticipationType() {
        return this.activityParticipationTypeRepo.getActivityParticipationType();
    }

    @Override
    public void addOrUpdate(ActivityParticipationType activityParticipationType) {
        this.activityParticipationTypeRepo.addOrUpdate(activityParticipationType);
    }

    @Override
    public ActivityParticipationType getActivityParticipationTypeById(int id) {
        return this.activityParticipationTypeRepo.getActivityParticipationTypeById(id);
    }

    @Override
    public List<ActivityParticipationType> getActivityParticipationTypesByActivityId(int activityId) {
        return this.activityParticipationTypeRepo.getActivityParticipationTypesByActivityId(activityId);
    }

    @Override
    public boolean existsByActivityIdAndParticipationTypeId(int activityId, int participationTypeId) {
        return this.activityParticipationTypeRepo.existsByActivityIdAndParticipationTypeId(activityId, participationTypeId);
    }

    @Override
    public ActivityParticipationType getActivityParticipationTypeByActivityIdAndParticipationTypeId(int activityId, int participationTypeId) {
        return this.activityParticipationTypeRepo.getActivityParticipationTypeByActivityIdAndParticipationTypeId(activityId, participationTypeId);
    }

    @Override
    public void deleteActivityParticipationType(int id) {
        this.activityParticipationTypeRepo.deleteActivityParticipationType(id);
    }
    
    @Override
    public List<ActivityParticipationTypeDTO> getActivityParticipationTypeDTOs() {
        List<ActivityParticipationType> list = this.getActivityParticipationType();
        return list.stream().map((f) -> convertToDTO(f, false, 0)).collect(Collectors.toList());
    }

    @Override
    public ActivityParticipationTypeDTO convertToDTO(ActivityParticipationType type, Boolean isGetReport, int studentId) {
        ActivityParticipationTypeDTO dto = new ActivityParticipationTypeDTO();
        Activity a = type.getActivityId();
        
        if(studentId != 0){
            Registration r = this.registrationService.findByStudentIdAndActivityParticipationTypeId(studentId, type.getId());
            
            if(r != null){
                dto.setIsRegistered(true);
                
                if(r.getParticipated()){
                    dto.setIsParticipated(true);
                }
                else dto.setIsParticipated(false);
            } else dto.setIsRegistered(false);
        }
        dto.setId(type.getId());
        dto.setPoint(type.getPoint());
        dto.setParticipationType(type.getParticipationTypeId().getName());
        
        if(isGetReport){
            dto.setActivity(this.acService.convertToRegistrationActivityDTO(type.getActivityId()));
        }
        return dto;
    }
    
}
