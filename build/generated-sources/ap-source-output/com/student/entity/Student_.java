package com.student.entity;

import com.student.entity.Branch;
import com.student.entity.Discuss;
import com.student.entity.News;
import com.student.entity.Replay;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-12T15:09:29")
@StaticMetamodel(Student.class)
public class Student_ { 

    public static volatile CollectionAttribute<Student, Replay> replayCollection;
    public static volatile SingularAttribute<Student, String> password;
    public static volatile SingularAttribute<Student, Integer> saccess;
    public static volatile CollectionAttribute<Student, News> newsCollection;
    public static volatile SingularAttribute<Student, String> sname;
    public static volatile CollectionAttribute<Student, Discuss> discussCollection;
    public static volatile SingularAttribute<Student, String> position;
    public static volatile SingularAttribute<Student, Branch> bid;
    public static volatile SingularAttribute<Student, String> sid;

}