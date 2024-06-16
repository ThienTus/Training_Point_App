package com.pbthnxl.pojo;

import com.pbthnxl.pojo.ActivityParticipationType;
import com.pbthnxl.pojo.Student;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-16T12:25:07")
=======
@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-16T00:49:53")
>>>>>>> 67e0b37 (commit)
@StaticMetamodel(Registration.class)
public class Registration_ { 

    public static volatile SingularAttribute<Registration, Student> studentId;
    public static volatile SingularAttribute<Registration, Boolean> participated;
    public static volatile SingularAttribute<Registration, Date> registrationDate;
    public static volatile SingularAttribute<Registration, ActivityParticipationType> activityParticipationTypeId;
    public static volatile SingularAttribute<Registration, Integer> id;

}