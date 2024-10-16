/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.Activity;
import com.dtt.pojo.User;
import com.dtt.services.ActivityParticipationTypeService;
import com.dtt.services.ActivityService;
import com.dtt.services.ArticleService;
import com.dtt.services.FacultyService;
import com.dtt.services.ParticipantService;
import com.dtt.services.ParticipationTypeService;
import com.dtt.services.UserService;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ThienTu
 */

@Controller
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    
    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private FacultyService facultyService;
    
    @Autowired
    private ParticipantService participantService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ParticipationTypeService participantTypeService;
    
    @Autowired
    private ActivityParticipationTypeService activityParticipationTypeService;
    
    @ModelAttribute
    public void commonAttr(Model model, Principal principal) {
        model.addAttribute("articles", this.articleService.getArticles());
        model.addAttribute("faculties", this.facultyService.getFaculties());
        model.addAttribute("participants", this.participantService.getParticipants());
        model.addAttribute("participantTypes", this.participantTypeService.getParticipationTypes());
        
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        model.addAttribute("userId", user.getId());
    }
    
    @GetMapping("/activities")
    public String createView(Model model) {
        model.addAttribute("activity", new Activity());
        return "activities";
    }
    
    @PostMapping("/activities")
    public String createActivity(@ModelAttribute(value = "activity") @Valid Activity a,
            BindingResult rs, Principal p) {
        if(p == null) return "redirect:/";
        User user = userService.getUserByUsername(p.getName());
        if(user == null || user.getUserRole().equals("ROLE_USER")){
            return "redirect:/";
        }
        if (!rs.hasErrors()) {
            try {
                this.activityService.addOrUpdate(a);

                return "redirect:/";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return "activities";
    }
    
    @GetMapping("/activities/{activityId}")
    public String updateView(Model model, @PathVariable(value = "activityId") int id, Principal p) {
        if(p == null) return "redirect:/";
        User user = userService.getUserByUsername(p.getName());
        if(user == null || user.getUserRole().equals("ROLE_USER")){
            return "redirect:/";
        }
        model.addAttribute("activity", this.activityService.getActivityById(id));
        
        return "activities";
    }
    
    @GetMapping("/activities/{activityId}/participation-type")
    public String addParticipationType(Model model, @PathVariable(value = "activityId") int id, Principal p) {
        if(p == null) return "redirect:/";
        User user = userService.getUserByUsername(p.getName());
        if(user == null || user.getUserRole().equals("ROLE_USER")){
            return "redirect:/";
        }
        System.out.println(this.activityParticipationTypeService.getActivityParticipationTypesByActivityId(id));
        model.addAttribute("activitiesParticipationType", this.activityParticipationTypeService.getActivityParticipationTypesByActivityId(id));
        return "activitiesParticipationType";
    }
    
    @GetMapping("/delete-activity/{id}")
    public String deleteActivity(@PathVariable("id") int id, Principal p) {
        if(p == null) return "redirect:/";
        User user = userService.getUserByUsername(p.getName());
        if(user == null || user.getUserRole().equals("ROLE_USER")){
            return "redirect:/";
        }
        this.activityService.deleteActivity(id);
        return "redirect:/";
    }
}
