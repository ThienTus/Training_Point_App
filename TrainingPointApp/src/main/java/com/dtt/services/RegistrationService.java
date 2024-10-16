/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtt.services;

import com.dtt.dto.RegistrationDTO;
import com.dtt.pojo.Registration;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ThienTu
 */

public interface RegistrationService {
    void save(Registration registration);
    Registration findByStudentIdAndActivityParticipationTypeId(Integer studentId, int activityParticipationTypeId);
    void processCSV(MultipartFile file, int activityParticipationTypeId);
    List<Registration> findRegistrationsByStudentId(int id, Map<String, String> params);
    List<RegistrationDTO> findRegistrationsByStudentIdDTO(int id, Map<String, String> params);
    Registration findRegistrationOwner(int studentId, int registrationId);
    Registration findRegistrationById(int id);
    void delete(int id);
    RegistrationDTO convertToDTO(Registration r);
    List<Registration> filterRegistrationsBySemester(List<Registration> registrations, int semesterId);
}
