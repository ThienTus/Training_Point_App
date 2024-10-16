/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.dtt.pojo.Semester;
import com.dtt.repositories.SemesterRepository;
import com.dtt.services.SemesterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThienTu
 */

@Service
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private SemesterRepository semesterRepo;

    @Override
    public List<Semester> getSemesters() {
        return this.semesterRepo.getSemesters();
    }

    @Override
    public Semester getSemesterById(int id) {
        return this.semesterRepo.getSemesterById(id);
    }

    @Override
    public void addOrUpdate(Semester s) {
        this.semesterRepo.addOrUpdate(s);
    }

    @Override
    public void delete(Semester s) {
        this.semesterRepo.delete(s);
    }

    @Override
    public Semester findByName(int name) {
        return this.semesterRepo.findByName(name);
    }
}
