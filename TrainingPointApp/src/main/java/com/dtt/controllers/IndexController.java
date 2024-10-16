/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.Activity;
import com.dtt.pojo.Article;
import com.dtt.pojo.Faculty;
import com.dtt.pojo.User;
import com.dtt.services.ActivityParticipationTypeService;
import com.dtt.services.ActivityService;
import com.dtt.services.ArticleService;
import com.dtt.services.ClassService;
import com.dtt.services.FacultyService;
import com.dtt.services.SemesterService;
import com.dtt.services.StatisticService;
import com.dtt.services.UserService;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ThienTu
 */

@Controller
@ControllerAdvice
public class IndexController {

    @Autowired
    private ActivityParticipationTypeService activityParticipationTypeService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private UserService userService;

    @Autowired
    private StatisticService statsService;

    @Autowired
    private ClassService classService;

    @ModelAttribute
    public void commonAttr(Model model, Principal principal) {
        model.addAttribute("faculties", this.facultyService.getFaculties());
        model.addAttribute("semesters", this.semesterService.getSemesters());
        if (principal != null) {
            model.addAttribute("user", userService.getUserByUsername(principal.getName()));
        }
    }

    @GetMapping("/")
    public String index(Model model,
            @RequestParam Map<String, String> params) {
        if(!params.containsKey("page")){
            params.put("page", "1");
        }
        model.addAttribute("activities", this.activityService.findFilteredActivities(params));
        model.addAttribute("articles", this.articleService.getArticles());
        return "index";
    }

//    @GetMapping("/stats")
//    public String stats(Principal p) {
//        if (p == null) {
//            return "redirect:/";
//        }
//        User user = userService.getUserByUsername(p.getName());
//        if (user == null || user.getUserRole().equals("ROLE_USER")) {
//            return "redirect:/";
//        }
//        return "stats";
//    }

    @GetMapping("/stats")
    public String classStats(Principal p, @RequestParam Map<String, String> params, Model model) {
        if (p == null) {
            return "redirect:/";
        }
        User user = userService.getUserByUsername(p.getName());
        if (user == null || user.getUserRole().equals("ROLE_USER")) {
            return "redirect:/";
        }
        int semesterId = 0;
        if (params.containsKey("semesterId")) {
            semesterId = Integer.parseInt(params.get("semesterId"));
        }
        int classId;
        if (params.containsKey("className")) {
            com.dtt.pojo.Class c = this.classService.findByName(params.get("className"));
            if(c != null){
                classId = c.getId();
                model.addAttribute("data", this.statsService.getStatisticsForClass(classId, semesterId));
                model.addAttribute("className", c.getName());
                model.addAttribute("semesterId", semesterId);

                return "classStats";
            } else return "stats";
        } else {
            return "stats";
        }
    }
}
