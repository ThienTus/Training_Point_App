/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtt.repositories;

import com.dtt.pojo.ReportMissing;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ThienTu
 */

public interface ReportMissingRepository {
    List<ReportMissing> getReportMissings();
    List<ReportMissing> getStudentReportMissings(int studentId,  Map<String, String> params);
    void confirmReportMissingById(int id);
    void rejectReportMissingById(int id);
    void save(ReportMissing r);
    ReportMissing findByStudentIdAndActivityParticipationTypeId(int studentId, int activityParticipationTypeId);
}
