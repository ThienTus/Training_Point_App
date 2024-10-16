/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.dto.ReportMissingDTO;
import com.dtt.pojo.ActivityParticipationType;
import com.dtt.pojo.Registration;
import com.dtt.pojo.ReportMissing;
import com.dtt.services.ActivityParticipationTypeService;
import com.dtt.services.CloudinaryService;
import com.dtt.services.RegistrationService;
import com.dtt.services.ReportMissingService;
import com.dtt.services.UserService;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ThienTu
 */

@RestController
@RequestMapping("/api/report")
public class ApiReportMissingController {

    @Autowired
    private UserService userService;
    @Autowired
    private ActivityParticipationTypeService acPartTypeSerivce;
    @Autowired
    private ReportMissingService reportService;
    @Autowired
    private CloudinaryService cloudinary;
    @Autowired
    private RegistrationService registrationService;

    @CrossOrigin
    @PostMapping(path = "/create/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<ReportMissingDTO> create(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] files, Principal p) {
        String proof = "";

        if (params.containsKey("acPartTypeId") && files.length > 0) {
            int studentId = this.userService.getUserByUsername(p.getName()).getStudent().getId();
            int acPartTypeId = Integer.parseInt(params.get("acPartTypeId"));
            ReportMissing r = this.reportService.
                    findByStudentIdAndActivityParticipationTypeId(studentId, acPartTypeId);

            Registration registration = this.registrationService.findByStudentIdAndActivityParticipationTypeId(studentId, acPartTypeId);

            proof = this.cloudinary.uploadFile(files[0]);

            if (r == null) {
                r = new ReportMissing();

                r.setStudentId(this.userService.getUserByUsername(p.getName()).getStudent());
                r.setActivityParticipationTypeId(this.acPartTypeSerivce.getActivityParticipationTypeById(acPartTypeId));
                r.setReportDate(new Date());
                r.setChecked(false);

                r.setProof(proof);
            } else if (r != null && registration.getParticipated() == false) {
                r.setReportDate(new Date());
                r.setChecked(false);

                r.setProof(proof);

            }

            this.reportService.save(r);

            return new ResponseEntity<>(this.reportService.convertToDTO(r), HttpStatus.CREATED);

        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @GetMapping(path = "/student-reports/")
    public ResponseEntity<List<ReportMissingDTO>> getUserReports(@RequestParam Map<String, String> params, Principal p) {
        
        if (p != null) {
            String page = params.get("page");
            int pg =0;
            if(page != null && !page.isEmpty()){
                pg = Integer.parseInt(page);
            }
            return new ResponseEntity<>(this.reportService.getStudentReportMissings(this.userService
                    .getUserByUsername(p.getName()).getStudent().getId(), params), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
