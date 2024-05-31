package com.pbthnxl.pojo;

import com.pbthnxl.pojo.ActivityParticipationType;
import com.pbthnxl.pojo.Student;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-29T06:12:58")
@StaticMetamodel(Registration.class)
public class Registration_ { 

    public static volatile SingularAttribute<Registration, Student> studentId;
    public static volatile SingularAttribute<Registration, Boolean> participated;
    public static volatile SingularAttribute<Registration, Date> registrationDate;
    public static volatile SingularAttribute<Registration, ActivityParticipationType> activityParticipationTypeId;
    public static volatile SingularAttribute<Registration, Integer> id;

}