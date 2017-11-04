package com.student.entity;

import com.student.entity.Discuss;
import com.student.entity.Student;
import com.student.entity.Type;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-12T15:09:29")
@StaticMetamodel(News.class)
public class News_ { 

    public static volatile SingularAttribute<News, Date> ndate;
    public static volatile SingularAttribute<News, String> ntitle;
    public static volatile SingularAttribute<News, Student> studentSid;
    public static volatile SingularAttribute<News, Integer> nid;
    public static volatile SingularAttribute<News, String> ncontext;
    public static volatile CollectionAttribute<News, Discuss> discussCollection;
    public static volatile SingularAttribute<News, Float> ngrade;
    public static volatile SingularAttribute<News, Type> tid;

}