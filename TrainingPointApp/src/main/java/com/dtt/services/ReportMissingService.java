/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtt.services;

import com.dtt.dto.ReportMissingDTO;
import com.dtt.pojo.ReportMissing;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ThienTu
 */

public interface ReportMissingService {
    List<ReportMissing> getReportMissings();
    List<ReportMissingDTO> getStudentReportMissings(int studentId, Map<String, String> params);
    void confirmReportMissingById(int id);
    void rejectReportMissingById(int id);
    void save(ReportMissing r);
    ReportMissingDTO convertToDTO(ReportMissing r);
    ReportMissing findByStudentIdAndActivityParticipationTypeId(int studentId, int activityParticipationTypeId);
}
