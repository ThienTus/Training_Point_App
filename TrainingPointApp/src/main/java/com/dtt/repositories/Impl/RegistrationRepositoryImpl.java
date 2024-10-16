/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.repositories.Impl;

import com.dtt.pojo.Registration;
import com.dtt.pojo.ReportMissing;
import com.dtt.pojo.Semester;
import com.dtt.pojo.Student;
import com.dtt.repositories.ActivityParticipationTypeRepository;
import com.dtt.repositories.RegistrationRepository;
import com.dtt.repositories.ReportMissingRepository;
import com.dtt.repositories.SemesterRepository;
import com.dtt.repositories.StudentRepository;
import com.dtt.services.SemesterService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.NoResultException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ThienTu
 */

@Repository
@Transactional
@PropertySource("classpath:paginations.properties")
public class RegistrationRepositoryImpl implements RegistrationRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ActivityParticipationTypeRepository activityParticipationTypeRepository;

    @Autowired
    private ReportMissingRepository reportRepo;

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private SemesterRepository semesterRepo;

    @Autowired
    private Environment env;

    @Override
    public void save(Registration registration) {
        Session s = factory.getObject().getCurrentSession();

        s.saveOrUpdate(registration);
    }

    @Override
    public void processCSV(MultipartFile file, int activityParticipationTypeId) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            Date currentDate = new Date();

            for (CSVRecord record : records) {
                try {
                    String studentCode = record.get("MSSV");
                    Student student = studentRepository.findByStudentCode(studentCode);

                    if (student != null) {
                        Registration registration = this.findByStudentIdAndActivityParticipationTypeId(student.getId(), activityParticipationTypeId);

                        ReportMissing report = this.reportRepo.findByStudentIdAndActivityParticipationTypeId(student.getId(), activityParticipationTypeId);

                        // Kiểm tra xem đã tồn tại registration cho student và activityParticipationType này chưa
                        if (report != null) {
                            report.setChecked(true);

                            this.reportRepo.save(report);
                        }
                        if (registration == null) {
                            registration = new Registration();
                            registration.setRegistrationDate(currentDate);
                            registration.setParticipated(true);
                            registration.setActivityParticipationTypeId(this.activityParticipationTypeRepository.getActivityParticipationTypeById(activityParticipationTypeId));
                            registration.setStudentId(student);
                            this.save(registration);
                        } else {
                            registration.setParticipated(true);

                            this.save(registration);
                        }
                    } else {
                        System.out.println("Student not found for MSSV: " + studentCode);
                    }
                } catch (Exception e) {
                    System.out.println("Error processing record: " + record);
                    e.printStackTrace();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(RegistrationRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Registration> getRegistrations() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Registration.findAll");
        return q.getResultList();
    }

    @Override
    public List<Registration> getRegistrationsByFacultyId(int facultyId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query query = s.createQuery("SELECT r FROM Registration r "
                + "JOIN FETCH r.studentId s "
                + "JOIN FETCH s.facultyId f "
                + "WHERE f.id = :facultyId AND r.participated = true", Registration.class);
        query.setParameter("facultyId", facultyId);

        return query.getResultList();
    }

    @Override
    public List<Registration> findRegistrationsByStudentId(int id, Map<String, String> params) {
        Session s = factory.getObject().getCurrentSession();

        StringBuilder hql = new StringBuilder("FROM Registration r WHERE r.studentId.id = :studentId");

        // loc theo hoc ky
        if (params.containsKey("semesterId")) {
            hql.append(" AND EXISTS (");
            hql.append("SELECT 1 FROM Semester sem WHERE sem.id = :semesterId");
            hql.append(" AND r.registrationDate BETWEEN sem.startDate AND sem.endDate)");
        }

        hql.append(" ORDER BY r.registrationDate DESC");

        Query<Registration> query = s.createQuery(hql.toString(), Registration.class);
        query.setParameter("studentId", id);

        if (params.containsKey("semesterId")) {
            query.setParameter("semesterId", Integer.parseInt(params.get("semesterId")));
        }

        // Pagination
        String page = params.get("page");
        if (page != null && !page.isEmpty()) {
            int pageSize = Integer.parseInt(env.getProperty("default.pageSize"));
            int pg = Integer.parseInt(page);
            int start = (pg - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);
        }

        return query.getResultList();
    }

    @Override
    public Registration findRegistrationById(int id) {
        Session s = factory.getObject().getCurrentSession();

        return s.get(Registration.class, id);
    }

    @Override
    public void delete(int id) {
        Session s = factory.getObject().getCurrentSession();

        Registration r = s.get(Registration.class, id);
        if (r != null) {
            s.delete(r);
        }
    }

    @Override
    public Registration findByStudentIdAndActivityParticipationTypeId(Integer studentId, int activityParticipationTypeId) {
        Session s = factory.getObject().getCurrentSession();
        Query<Registration> query = s.createQuery(
                "SELECT r FROM Registration r WHERE r.studentId.id = :studentId AND r.activityParticipationTypeId.id = :activityParticipationTypeId",
                Registration.class
        );
        query.setParameter("studentId", studentId);
        query.setParameter("activityParticipationTypeId", activityParticipationTypeId);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public Registration findRegistrationOwner(int studentId, int registrationId) {
        Session s = factory.getObject().getCurrentSession();

        Query<Registration> q = s.createQuery("SELECT r FROM Registration r "
                + "WHERE r.id = :registrationId AND r.studentId.id = :studentId", Registration.class);

        q.setParameter("studentId", studentId);
        q.setParameter("registrationId", registrationId);

        return q.getSingleResult();
    }

    @Override
    public List<Registration> filterRegistrationsBySemester(List<Registration> registrations, int semesterId) {
        if (semesterId == 0) {
            return registrations;
        }

        Semester semester = semesterService.getSemesterById(semesterId);
        if (semester == null) {
            return registrations;
        }

        Date startDate = semester.getStartDate();
        Date endDate = semester.getEndDate();
        return registrations.stream()
                .filter(r -> r.getRegistrationDate().getTime() > startDate.getTime() && r.getRegistrationDate().getTime() < endDate.getTime())
                .collect(Collectors.toList());
    }

    @Override
    public List<Registration> getRegistrationsByClassId(int classId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query query = s.createQuery("SELECT r FROM Registration r "
                + "JOIN FETCH r.studentId s "
                + "JOIN FETCH s.classId c "
                + "WHERE c.id = :classId AND r.participated = true", Registration.class);
        query.setParameter("classId", classId);
        return query.getResultList();
    }

}
