package com.student.entity;

import com.student.entity.News;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-12T15:09:29")
@StaticMetamodel(Type.class)
public class Type_ { 

    public static volatile CollectionAttribute<Type, News> newsCollection;
    public static volatile SingularAttribute<Type, String> tname;
    public static volatile SingularAttribute<Type, Integer> tid;

}