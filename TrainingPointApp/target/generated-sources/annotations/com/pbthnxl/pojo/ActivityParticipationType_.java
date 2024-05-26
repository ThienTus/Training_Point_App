package com.pbthnxl.pojo;

import com.pbthnxl.pojo.Activity;
import com.pbthnxl.pojo.ParticipationType;
import com.pbthnxl.pojo.Registration;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-26T17:29:45")
@StaticMetamodel(ActivityParticipationType.class)
public class ActivityParticipationType_ { 

    public static volatile SetAttribute<ActivityParticipationType, Registration> registrationSet;
    public static volatile SingularAttribute<ActivityParticipationType, Integer> id;
    public static volatile SingularAttribute<ActivityParticipationType, ParticipationType> participationTypeId;
    public static volatile SingularAttribute<ActivityParticipationType, Integer> point;
    public static volatile SingularAttribute<ActivityParticipationType, Activity> acitivityId;

}