/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mengjie.ejb;

import com.mengjie.entity.User;
import java.util.Set;
import java.util.jar.Attributes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Mengjie
 */
@Stateless
public class UserEntity {
    
    @PersistenceContext
    private EntityManager em;
    public String name="sbsbsbs";
    
    public void create(User user){
        try {
            //user.setId(123);
//            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//Validator validator = factory.getValidator();
//
//Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
//
//if (constraintViolations.size() > 0 ) {
//System.out.println("Constraint Violations occurred..");
//for (ConstraintViolation<User> contraints : constraintViolations) {
//System.out.println(contraints.getRootBeanClass().getSimpleName()+
//"." + contraints.getPropertyPath() + " " + contraints.getMessage());
//  }
//}
//            em.getTransaction().begin();
            em.persist(user);
           // em.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public EntityManager getEntityManager(){
        return em;
    }
}
