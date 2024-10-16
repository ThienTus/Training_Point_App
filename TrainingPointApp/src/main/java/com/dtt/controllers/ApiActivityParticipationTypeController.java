/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.dto.ActivityParticipationTypeDTO;
import com.dtt.services.ActivityParticipationTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ThienTu
 */

@RestController
@RequestMapping("/api/activity-participation-type")
public class ApiActivityParticipationTypeController {
    @Autowired
    private ActivityParticipationTypeService activityParticipationTypeService;
    
    
    @CrossOrigin
    @RequestMapping("/list/")
    public ResponseEntity<List<ActivityParticipationTypeDTO>> List(){
        return new ResponseEntity<>(this.activityParticipationTypeService.getActivityParticipationTypeDTOs(), HttpStatus.OK);
    }
}
