/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtt.services;

import com.dtt.pojo.Student;
import java.util.List;

/**
 *
 * @author ThienTu
 */

public interface StudentService {
    boolean isExistingStudentCode(String studentCode);
    void saveStudent(Student student);
    List<Student> findAllAssistants();
    List<Student> getStudentList(int facultyId, int page);
    Student getStudentById(int id);
}
