/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.controllers;

import com.dtt.components.JwtService;
import com.dtt.dto.AssistantDTO;
import com.dtt.dto.StudentUserDTO;
import com.dtt.pojo.Student;
import com.dtt.pojo.User;
import com.dtt.services.ClassService;
import com.dtt.services.CloudinaryService;
import com.dtt.services.FacultyService;
import com.dtt.services.StudentService;
import com.dtt.services.UserService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ThienTu
 */

@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private CloudinaryService cloudinary;
    @Autowired
    private ClassService classService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private StudentService studentService;

    @PostMapping(path = "/register/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @CrossOrigin
    @Transactional
    public ResponseEntity<String> create(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] files) {
        String avatarUrl = "";

        // Create user
        User user = new User();
        user.setFirstName(params.get("firstName"));
        user.setLastName(params.get("lastName"));
        user.setUsername(params.get("username"));
        user.setPassword(this.passwordEncoder.encode(params.get("password")));
        user.setEmail(params.get("email"));
        user.setPhoneNumber(params.get("phone"));
        user.setUserRole("ROLE_USER");
        user.setActive(true);
        if (files.length > 0) {
            avatarUrl = this.cloudinary.uploadFile(files[0]);
            user.setAvatar(avatarUrl);
        }
        this.userService.saveUser(user);
        
        
        this.userService.saveUser(user);
        // Create student
        
        Student student = new Student();
        student.setStudentCode(params.get("studentCode"));
        student.setClassId(classService.getClassById(Integer.parseInt(params.get("classId"))));
        student.setFacultyId(facultyService.getFacultyById(Integer.parseInt(params.get("facultyId"))));
        student.setUserId(user);
        
        
        this.studentService.saveStudent(student);
        return new ResponseEntity<>(user.getAvatar(), HttpStatus.CREATED);
    }

    @PostMapping("/login/")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody User user) {
        if (this.userService.authUser(user.getUsername(), user.getPassword()) == true) {

            String token = this.jwtService.generateTokenLogin(user.getUsername());

            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/current-user/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<StudentUserDTO> getCurrentUser(Principal p) {
        StudentUserDTO u = this.userService.getUserByUsernameDTO(p.getName());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PatchMapping(path = "/current-user/", consumes = {
        MediaType.MULTIPART_FORM_DATA_VALUE,
        MediaType.APPLICATION_JSON_VALUE,
    })
    @CrossOrigin
    public ResponseEntity<StudentUserDTO> update(@RequestParam Map<String, String> params, @RequestPart(required = false) MultipartFile[] files, Principal p) {
        User user = this.userService.getUserByUsername(p.getName());
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if (params.containsKey("password")) {
            user.setPassword(this.passwordEncoder.encode(params.get("password")));
        }
        if (params.containsKey("firstName")) {
            user.setFirstName(params.get("firstName"));
        }
        if (params.containsKey("lastName")) {
            user.setLastName(params.get("lastName"));
        }
        if (params.containsKey("phone")) {
            user.setPhoneNumber(params.get("phone"));
        }
        
        if (files != null && files.length > 0) {
            String avatarUrl = this.cloudinary.uploadFile(files[0]);
            user.setAvatar(avatarUrl);
        }

        this.userService.saveUser(user);

        return new ResponseEntity<>(this.userService.getUserByUsernameDTO(user.getUsername()), HttpStatus.OK);
    }
    
    @PatchMapping(path = "/current-user/change-password/", consumes = {
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @CrossOrigin
    public ResponseEntity<String> changePassword(@RequestParam Map<String, String> params, Principal p){
        User user = this.userService.getUserByUsername(p.getName());
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        
        String oldPassword = "";
        if (params.containsKey("old_password") && params.containsKey("new_password")){
            oldPassword = params.get("old_password");
            if(this.passwordEncoder.matches(oldPassword, user.getPassword())){
                 user.setPassword(this.passwordEncoder.encode(params.get("new_password")));
                 this.userService.saveUser(user);
                 return new ResponseEntity<>("Success", HttpStatus.OK);
            }
        } 
        
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/assistant-list/")
    @CrossOrigin
    public ResponseEntity<List<AssistantDTO>> assistantList(){
        return new ResponseEntity<>(this.userService.getAssistantsDTO(), HttpStatus.OK);
    }
}
