package com.pbthnxl.pojo;

import com.pbthnxl.pojo.Activity;
import com.pbthnxl.pojo.Student;
import com.pbthnxl.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-26T17:29:45")
@StaticMetamodel(ReportMissing.class)
public class ReportMissing_ { 

    public static volatile SingularAttribute<ReportMissing, Student> studentId;
    public static volatile SingularAttribute<ReportMissing, Activity> activityId;
    public static volatile SingularAttribute<ReportMissing, Date> reportDate;
    public static volatile SingularAttribute<ReportMissing, byte[]> checked;
    public static volatile SingularAttribute<ReportMissing, Integer> id;
    public static volatile SingularAttribute<ReportMissing, String> proof;
    public static volatile SingularAttribute<ReportMissing, User> userId;

}