package com.pbthnxl.pojo;

import com.pbthnxl.pojo.Activity;
import com.pbthnxl.pojo.ParticipationType;
import com.pbthnxl.pojo.Registration;
import com.pbthnxl.pojo.ReportMissing;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-09T00:03:48")
@StaticMetamodel(ActivityParticipationType.class)
public class ActivityParticipationType_ { 

    public static volatile SingularAttribute<ActivityParticipationType, Activity> activityId;
    public static volatile SetAttribute<ActivityParticipationType, Registration> registrationSet;
    public static volatile SetAttribute<ActivityParticipationType, ReportMissing> reportMissingSet;
    public static volatile SingularAttribute<ActivityParticipationType, Integer> id;
    public static volatile SingularAttribute<ActivityParticipationType, ParticipationType> participationTypeId;
    public static volatile SingularAttribute<ActivityParticipationType, Integer> point;

}