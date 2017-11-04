package com.student.entity;

import com.student.entity.Discuss;
import com.student.entity.Student;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-12T15:09:29")
@StaticMetamodel(Replay.class)
public class Replay_ { 

    public static volatile SingularAttribute<Replay, String> rcontext;
    public static volatile SingularAttribute<Replay, Date> rdate;
    public static volatile SingularAttribute<Replay, Student> studentSid;
    public static volatile SingularAttribute<Replay, Integer> rid;
    public static volatile SingularAttribute<Replay, Discuss> discussDid;

}