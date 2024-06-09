package com.pbthnxl.pojo;

import com.pbthnxl.pojo.Activity;
import com.pbthnxl.pojo.Comment;
import com.pbthnxl.pojo.Interaction;
import com.pbthnxl.pojo.ReportMissing;
import com.pbthnxl.pojo.Student;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-09T00:03:48")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SetAttribute<User, Activity> activitySet;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, Student> student;
    public static volatile SingularAttribute<User, Boolean> active;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SetAttribute<User, ReportMissing> reportMissingSet;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> phoneNumber;
    public static volatile SetAttribute<User, Interaction> interactionSet;
    public static volatile SetAttribute<User, Comment> commentSet;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> userRole;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}