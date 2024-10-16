/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.pojo.Article;
import com.dtt.pojo.Faculty;
import com.dtt.pojo.Class;
import com.dtt.pojo.Semester;
import com.dtt.services.ArticleService;
import com.dtt.services.ClassService;
import com.dtt.services.FacultyService;
import com.dtt.services.SemesterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ThienTu
 */

@RestController
@RequestMapping("/api/common")
public class ApiCommonController {
    @Autowired
    private FacultyService faService;
    @Autowired
    private ArticleService arService;
    @Autowired
    private ClassService classService;
    @Autowired
    private SemesterService semesterService;
    
    @CrossOrigin
    @GetMapping("/faculty-list/")
    public ResponseEntity<List<Faculty>> FacultyList(){
        return new ResponseEntity<>(this.faService.getFaculties(), HttpStatus.OK);
    }
    
    @CrossOrigin
    @GetMapping("/article-list/")
    public ResponseEntity<List<Article>> ArticleList(){
        return new ResponseEntity<>(this.arService.getArticles(), HttpStatus.OK);
    }
    
    @CrossOrigin
    @GetMapping("/class-list")
    public ResponseEntity<List<Class>> ClassList(){
        return new ResponseEntity<>(this.classService.getClasses(), HttpStatus.OK);
    }
    
    @CrossOrigin
    @GetMapping("/semester-list/")
    public ResponseEntity<List<Semester>> SemesterList(){
        return new ResponseEntity<>(this.semesterService.getSemesters(), HttpStatus.OK);
    }
    
}
