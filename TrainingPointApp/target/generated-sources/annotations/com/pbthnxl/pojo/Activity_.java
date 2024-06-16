package com.pbthnxl.pojo;

import com.pbthnxl.pojo.ActivityParticipationType;
import com.pbthnxl.pojo.Article;
import com.pbthnxl.pojo.Comment;
import com.pbthnxl.pojo.Faculty;
import com.pbthnxl.pojo.Interaction;
import com.pbthnxl.pojo.Participant;
import com.pbthnxl.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-16T13:47:42")
@StaticMetamodel(Activity.class)
public class Activity_ { 

    public static volatile SingularAttribute<Activity, Participant> participantId;
    public static volatile SingularAttribute<Activity, Faculty> facultyId;
    public static volatile SingularAttribute<Activity, Date> startDateTime;
    public static volatile SetAttribute<Activity, Interaction> interactionSet;
    public static volatile SetAttribute<Activity, Comment> commentSet;
    public static volatile SingularAttribute<Activity, Date> endDate;
    public static volatile SetAttribute<Activity, ActivityParticipationType> activityParticipationTypeSet;
    public static volatile SingularAttribute<Activity, Article> articleId;
    public static volatile SingularAttribute<Activity, String> name;
    public static volatile SingularAttribute<Activity, String> location;
    public static volatile SingularAttribute<Activity, Integer> id;
    public static volatile SingularAttribute<Activity, User> userId;

}