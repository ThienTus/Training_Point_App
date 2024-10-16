/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services;

import com.dtt.dto.ActivityParticipationTypeDTO;
import com.dtt.pojo.ActivityParticipationType;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ThienTu
 */

public interface ActivityParticipationTypeService {
    List<ActivityParticipationType> getActivityParticipationType();
    List<ActivityParticipationType> getActivityParticipationTypesByActivityId(int activityId);
    void addOrUpdate(ActivityParticipationType activityParticipationType);
    ActivityParticipationType getActivityParticipationTypeById(int id);
    boolean existsByActivityIdAndParticipationTypeId(int activityId, int participationTypeId);
    ActivityParticipationType getActivityParticipationTypeByActivityIdAndParticipationTypeId(int activityId, int participationTypeId);
    void deleteActivityParticipationType(int id);
    List<ActivityParticipationTypeDTO> getActivityParticipationTypeDTOs();
    ActivityParticipationTypeDTO convertToDTO(ActivityParticipationType type, Boolean isGetReport, int studentId);
}
