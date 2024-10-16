/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.services.impl;

import com.dtt.pojo.Registration;
import com.dtt.pojo.Semester;
import com.dtt.pojo.Student;
import com.dtt.repositories.RegistrationRepository;
import com.dtt.services.SemesterService;
import com.dtt.services.StatisticService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThienTu
 */

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private SemesterService semesterService;

    @Override
    public Map<String, Object> getStatisticsByFacultyAndClass() {
        List<Registration> registrations = registrationRepository.getRegistrations();

        Map<String, Map<String, Integer>> facultyClassStats = new HashMap<>();

        for (Registration registration : registrations) {
            if (!registration.getParticipated()) {
                continue;
            }
            String facultyName = registration.getStudentId().getFacultyId().getName();
            String className = registration.getStudentId().getClassId().getName();
            int points = registration.getActivityParticipationTypeId().getPoint();

            facultyClassStats.putIfAbsent(facultyName, new HashMap<>());
            Map<String, Integer> classStats = facultyClassStats.get(facultyName);
            classStats.put(className, classStats.getOrDefault(className, 0) + points);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("facultyClassStats", facultyClassStats);
        return result;
    }

    @Override
    public Map<String, Object> getStatisticsForAllFaculties() {
        Map<String, Object> data = new HashMap<>();

        return data;
    }

    @Override
    public Map<String, Integer> getStatisticsForFaculty(int id) {
        Map<String, Integer> data = new HashMap<>();
        List<Registration> registrations = registrationRepository.getRegistrationsByFacultyId(id);
        for (Registration registration : registrations) {
            if (!registration.getParticipated()) {
                continue;
            }
            String className = registration.getStudentId().getClassId().getName();
            int points = registration.getActivityParticipationTypeId().getPoint();

            data.put(className, data.getOrDefault(className, 0) + points);
        }
        return data;
    }

    @Override
    public Map<String, Integer> getClassificationStatistics(int facultyId) {
        List<Registration> registrations;
        if (facultyId == 0) {
            registrations = registrationRepository.getRegistrations();
        } else {
            registrations = registrationRepository.getRegistrationsByFacultyId(facultyId);
        }

        Map<Integer, Integer> studentTotalPoints = new HashMap<>();
        for (Registration registration : registrations) {
            if (registration.getParticipated()) {
                int studentId = registration.getStudentId().getId();
                int points = registration.getActivityParticipationTypeId().getPoint();

                studentTotalPoints.put(studentId, studentTotalPoints.getOrDefault(studentId, 0) + points);
            }
        }

        Map<String, Integer> classificationStats = new HashMap<>();
        classificationStats.put("Xuất sắc", 0);
        classificationStats.put("Tốt", 0);
        classificationStats.put("Khá", 0);
        classificationStats.put("Trung bình", 0);
        classificationStats.put("Yếu", 0);
        classificationStats.put("Kém", 0);

        for (int totalPoints : studentTotalPoints.values()) {
            if (totalPoints >= 90) {
                classificationStats.put("Xuất sắc", classificationStats.get("Xuất sắc") + 1);
            } else if (totalPoints >= 80) {
                classificationStats.put("Tốt", classificationStats.get("Tốt") + 1);
            } else if (totalPoints >= 65) {
                classificationStats.put("Khá", classificationStats.get("Khá") + 1);
            } else if (totalPoints >= 50) {
                classificationStats.put("Trung bình", classificationStats.get("Trung bình") + 1);
            } else if (totalPoints >= 35) {
                classificationStats.put("Yếu", classificationStats.get("Yếu") + 1);
            } else {
                classificationStats.put("Kém", classificationStats.get("Kém") + 1);
            }
        }

        return classificationStats;
    }

    @Override
    public Map<String, Object> getStatisticsByFacultyAndClass(int semesterId) {
        List<Registration> registrations = registrationRepository.getRegistrations();
        registrations = this.registrationRepository.filterRegistrationsBySemester(registrations, semesterId);

        Map<String, Map<String, Integer>> facultyClassStats = new HashMap<>();

        for (Registration registration : registrations) {
            if (!registration.getParticipated()) {
                continue;
            }
            String facultyName = registration.getStudentId().getFacultyId().getName();
            String className = registration.getStudentId().getClassId().getName();
            int points = registration.getActivityParticipationTypeId().getPoint();

            facultyClassStats.putIfAbsent(facultyName, new HashMap<>());
            Map<String, Integer> classStats = facultyClassStats.get(facultyName);
            classStats.put(className, classStats.getOrDefault(className, 0) + points);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("facultyClassStats", facultyClassStats);
        return result;
    }

    @Override
    public Map<String, Integer> getStatisticsForFaculty(int id, int semesterId) {
        Map<String, Integer> data = new HashMap<>();
        List<Registration> registrations;
        if (id == 0) {
            registrations = this.registrationRepository.getRegistrations();
        } else {
            registrations = this.registrationRepository.getRegistrationsByFacultyId(id);
        }
        registrations = this.registrationRepository.filterRegistrationsBySemester(registrations, semesterId);

        for (Registration registration : registrations) {
            if (!registration.getParticipated()) {
                continue;
            }
            String className = registration.getStudentId().getClassId().getName();
            int points = registration.getActivityParticipationTypeId().getPoint();

            data.put(className, data.getOrDefault(className, 0) + points);
        }
        return data;
    }

    @Override
    public Map<String, Integer> getClassificationStatistics(int facultyId, int semesterId) {
        List<Registration> registrations;
        if (facultyId == 0) {
            registrations = this.registrationRepository.getRegistrations();
        } else {
            registrations = this.registrationRepository.getRegistrationsByFacultyId(facultyId);
        }

        registrations = this.registrationRepository.filterRegistrationsBySemester(registrations, semesterId);

        Map<Integer, Integer> studentTotalPoints = new HashMap<>();
        for (Registration registration : registrations) {
            if (registration.getParticipated()) {
                int studentId = registration.getStudentId().getId();
                int points = registration.getActivityParticipationTypeId().getPoint();

                studentTotalPoints.put(studentId, studentTotalPoints.getOrDefault(studentId, 0) + points);
            }
        }

        Map<String, Integer> classificationStats = new HashMap<>();
        classificationStats.put("Xuất sắc", 0);
        classificationStats.put("Tốt", 0);
        classificationStats.put("Khá", 0);
        classificationStats.put("Trung bình", 0);
        classificationStats.put("Yếu", 0);
        classificationStats.put("Kém", 0);

        for (int totalPoints : studentTotalPoints.values()) {
            if (totalPoints >= 90) {
                classificationStats.put("Xuất sắc", classificationStats.get("Xuất sắc") + 1);
            } else if (totalPoints >= 80) {
                classificationStats.put("Tốt", classificationStats.get("Tốt") + 1);
            } else if (totalPoints >= 65) {
                classificationStats.put("Khá", classificationStats.get("Khá") + 1);
            } else if (totalPoints >= 50) {
                classificationStats.put("Trung bình", classificationStats.get("Trung bình") + 1);
            } else if (totalPoints >= 35) {
                classificationStats.put("Yếu", classificationStats.get("Yếu") + 1);
            } else {
                classificationStats.put("Kém", classificationStats.get("Kém") + 1);
            }
        }

        return classificationStats;
    }

    @Override
    public Map<String, Object> getStatisticsForClass(int classId, int semesterId) {
        Map<String, Object> data = new HashMap<>();
        List<Registration> registrations = new ArrayList<>();
        if (classId != 0) {
            registrations = this.registrationRepository.getRegistrationsByClassId(classId);
        } else registrations = this.registrationRepository.getRegistrations();
        registrations = this.registrationRepository.filterRegistrationsBySemester(registrations, semesterId);

        Map<Integer, Map<String, Object>> studentInfo = new HashMap<>();
        for (Registration registration : registrations) {
            if (registration.getParticipated()) {
                Integer studentId = registration.getStudentId().getId();
                int points = registration.getActivityParticipationTypeId().getPoint();

                if (!studentInfo.containsKey(studentId)) {
                    Map<String, Object> studentData = new HashMap<>();
                    studentData.put("student", registration.getStudentId());
                    studentData.put("points", points);
                    studentInfo.put(studentId, studentData);
                } else {
                    int currentPoints = (int) studentInfo.get(studentId).get("points");
                    studentInfo.get(studentId).put("points", currentPoints + points);
                }
            }
        }

        Map<String, Integer> classificationStats = new HashMap<>();
        classificationStats.put("Xuất sắc", 0);
        classificationStats.put("Tốt", 0);
        classificationStats.put("Khá", 0);
        classificationStats.put("Trung bình", 0);
        classificationStats.put("Yếu", 0);
        classificationStats.put("Kém", 0);

        for (Map<String, Object> info : studentInfo.values()) {
            int totalPoints = (int) info.get("points");
            if (totalPoints >= 90) {
                classificationStats.put("Xuất sắc", classificationStats.get("Xuất sắc") + 1);
            } else if (totalPoints >= 80) {
                classificationStats.put("Tốt", classificationStats.get("Tốt") + 1);
            } else if (totalPoints >= 65) {
                classificationStats.put("Khá", classificationStats.get("Khá") + 1);
            } else if (totalPoints >= 50) {
                classificationStats.put("Trung bình", classificationStats.get("Trung bình") + 1);
            } else if (totalPoints >= 35) {
                classificationStats.put("Yếu", classificationStats.get("Yếu") + 1);
            } else {
                classificationStats.put("Kém", classificationStats.get("Kém") + 1);
            }
        }
        data.put("stats", classificationStats);
        data.put("students", studentInfo);

        return data;
    }

}
