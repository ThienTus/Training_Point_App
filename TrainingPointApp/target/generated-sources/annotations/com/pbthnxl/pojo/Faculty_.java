package com.pbthnxl.pojo;

import com.pbthnxl.pojo.Activity;
import com.pbthnxl.pojo.Student;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-26T17:29:45")
@StaticMetamodel(Faculty.class)
public class Faculty_ { 

    public static volatile SetAttribute<Faculty, Activity> activitySet;
    public static volatile SetAttribute<Faculty, Student> studentSet;
    public static volatile SingularAttribute<Faculty, String> name;
    public static volatile SingularAttribute<Faculty, Integer> id;

}