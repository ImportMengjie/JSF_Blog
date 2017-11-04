package com.student.entity;

import com.student.entity.Student;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-12T15:09:29")
@StaticMetamodel(Branch.class)
public class Branch_ { 

    public static volatile SingularAttribute<Branch, String> bname;
    public static volatile CollectionAttribute<Branch, Student> studentCollection;
    public static volatile SingularAttribute<Branch, Integer> bid;

}