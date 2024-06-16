package com.pbthnxl.pojo;

import com.pbthnxl.pojo.Activity;
import com.pbthnxl.pojo.Interaction;
import com.pbthnxl.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-16T12:25:07")
=======
@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-16T00:49:53")
>>>>>>> 67e0b37 (commit)
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, Date> createdAt;
    public static volatile SingularAttribute<Comment, Activity> activityId;
    public static volatile SetAttribute<Comment, Interaction> interactionSet;
    public static volatile SingularAttribute<Comment, Integer> id;
    public static volatile SingularAttribute<Comment, User> userId;
    public static volatile SingularAttribute<Comment, String> content;

}