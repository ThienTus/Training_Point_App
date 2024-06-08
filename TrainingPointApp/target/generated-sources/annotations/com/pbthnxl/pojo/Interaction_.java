package com.pbthnxl.pojo;

import com.pbthnxl.pojo.Activity;
import com.pbthnxl.pojo.Comment;
import com.pbthnxl.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-08T13:47:09")
@StaticMetamodel(Interaction.class)
public class Interaction_ { 

    public static volatile SingularAttribute<Interaction, Date> createdAt;
    public static volatile SingularAttribute<Interaction, Activity> activityId;
    public static volatile SingularAttribute<Interaction, Comment> commentId;
    public static volatile SingularAttribute<Interaction, Integer> id;
    public static volatile SingularAttribute<Interaction, User> userId;

}