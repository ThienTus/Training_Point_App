/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtt.repositories;

import java.util.List;
import com.dtt.pojo.Class;

/**
 *
 * @author ThienTu
 */

public interface ClassRepository {
    List<Class> getClasses();
    Class getClassById(int id);
    void addOrUpdate(Class c);
    void delete(Class c);
    Class findByName(String name);
}
