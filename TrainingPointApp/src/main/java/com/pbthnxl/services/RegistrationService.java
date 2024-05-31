/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pbthnxl.services;

import com.pbthnxl.pojo.Registration;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author DELL
 */
public interface RegistrationService {
    void save(Registration registration);
    boolean existsByStudentIdAndActivityParticipationTypeId(Integer studentId, int activityParticipationTypeId);
    void processCSV(MultipartFile file, int activityParticipationTypeId);
}
