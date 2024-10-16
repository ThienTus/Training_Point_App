/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.dtt.pojo.Class;
import com.dtt.repositories.ClassRepository;
import com.dtt.services.ClassService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThienTu
 */

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;

    @Override
    public List<Class> getClasses() {
        return this.classRepository.getClasses();
    }

    @Override
    public Class getClassById(int id) {
        return this.classRepository.getClassById(id);
    }

    @Override
    public void addOrUpdate(Class c) {
        this.classRepository.addOrUpdate(c);
    }

    @Override
    public void delete(Class c) {
        this.classRepository.delete(c);
    }

    @Override
    public Class findByName(String name) {
        return this.classRepository.findByName(name);
    }
    
}
