package com.student.entity;

import com.student.entity.News;
import com.student.entity.Replay;
import com.student.entity.Student;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-12T15:09:29")
@StaticMetamodel(Discuss.class)
public class Discuss_ { 

    public static volatile CollectionAttribute<Discuss, Replay> replayCollection;
    public static volatile SingularAttribute<Discuss, Date> ddate;
    public static volatile SingularAttribute<Discuss, Student> studentSid;
    public static volatile SingularAttribute<Discuss, String> dcontext;
    public static volatile SingularAttribute<Discuss, Integer> did;
    public static volatile SingularAttribute<Discuss, News> newsNid;

}