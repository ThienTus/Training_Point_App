package com.pbthnxl.pojo;

import com.pbthnxl.pojo.Class;
import com.pbthnxl.pojo.Faculty;
import com.pbthnxl.pojo.Registration;
import com.pbthnxl.pojo.ReportMissing;
import com.pbthnxl.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-16T12:25:07")
@StaticMetamodel(Student.class)
public class Student_ { 

    public static volatile SingularAttribute<Student, Faculty> facultyId;
    public static volatile SetAttribute<Student, ReportMissing> reportMissingSet;
    public static volatile SetAttribute<Student, Registration> registrationSet;
    public static volatile SingularAttribute<Student, Class> classId;
    public static volatile SingularAttribute<Student, String> studentCode;
    public static volatile SingularAttribute<Student, Integer> id;
    public static volatile SingularAttribute<Student, User> userId;

}