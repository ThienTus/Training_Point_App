/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services;

import com.dtt.pojo.Semester;
import java.util.List;

/**
 *
 * @author ThienTu
 */

public interface SemesterService {
    List<Semester> getSemesters();
    Semester getSemesterById(int id);
    void addOrUpdate(Semester s);
    void delete(Semester s);
    Semester findByName(int name);
}
