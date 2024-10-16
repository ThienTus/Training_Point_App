/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtt.services;

import com.dtt.pojo.Faculty;
import java.util.List;

/**
 *
 * @author ThienTu
 */

public interface FacultyService {
    List<Faculty> getFaculties();
    Faculty getFacultyById(int id);
    void addOrUpdate(Faculty c);
    void delete(Faculty c);
    Faculty findByName(String name);
}
