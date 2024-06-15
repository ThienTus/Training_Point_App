package com.pbthnxl.pojo;

import com.pbthnxl.pojo.ActivityParticipationType;
import com.pbthnxl.pojo.Student;
import com.pbthnxl.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-15T13:23:43")
@StaticMetamodel(ReportMissing.class)
public class ReportMissing_ { 

    public static volatile SingularAttribute<ReportMissing, Student> studentId;
    public static volatile SingularAttribute<ReportMissing, Date> reportDate;
    public static volatile SingularAttribute<ReportMissing, Boolean> checked;
    public static volatile SingularAttribute<ReportMissing, ActivityParticipationType> activityParticipationTypeId;
    public static volatile SingularAttribute<ReportMissing, Integer> id;
    public static volatile SingularAttribute<ReportMissing, String> proof;
    public static volatile SingularAttribute<ReportMissing, User> userId;

}