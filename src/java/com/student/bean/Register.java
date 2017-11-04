/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student.bean;

import com.student.entity.Student;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


/**
 *
 * @author Mengjie
 */
@Named(value = "register")
@SessionScoped
public class Register implements Serializable {

    /**
     * Creates a new instance of Register
     */
    private String studentID;
    private String name;
    private String password="";
    private Student student = new Student();
    
    
    @PersistenceContext
    private EntityManager em;
    
    public Register() {
    }
    
    @Transactional
    public void inputNameID(ActionEvent actionEvent){
//        List<Student> students = em.createNamedQuery("Student.findBySid").setParameter("sid", studentID).getResultList();
//        if(students==null || students.size()<=0){
//            FacesContext.getCurrentInstance().addMessage("form_id:studentID", new FacesMessage(FacesMessage.SEVERITY_ERROR, "学号不存在!", ""));
//            FacesContext.getCurrentInstance().validationFailed();
//        }
//        else if(students.get(0).getPassword()!=null||"".equals(students.get(0).getPassword())){
//            FacesContext.getCurrentInstance().addMessage("form_id:studentID", new FacesMessage(FacesMessage.SEVERITY_ERROR, "该用户已经注册过了!", ""));
//            FacesContext.getCurrentInstance().validationFailed();
//        }
//        else if(!students.get(0).getSname().equals(name))
//        {
//            FacesContext.getCurrentInstance().addMessage("form_id:name", new FacesMessage(FacesMessage.SEVERITY_ERROR, "姓名与学号不匹配!", ""));
//            FacesContext.getCurrentInstance().validationFailed();
//        }
//        else{
//            this.student=students.get(0);
//            this.student.setPassword(password);
//           
//            em.merge(this.student);
//            FacesContext.getCurrentInstance().addMessage("form_id:validate_id", new FacesMessage(FacesMessage.SEVERITY_INFO, "注册成功", ""));
//            
//        }
    
        this.student.setPassword(password);
        em.merge(this.student);
        FacesContext.getCurrentInstance().addMessage("form_id:validate_id", new FacesMessage(FacesMessage.SEVERITY_INFO, "注册成功", ""));
    }
    
    public void validatePW(FacesContext context, UIComponent component,  
                     Object inputValue)
    {
        String pw=(String)inputValue;
        if(!this.password.equals(pw)){
           throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"两次密码不一致!",""));
        }
    }
    
     public void validateID(FacesContext context, UIComponent component,  
                     Object inputValue){
        String ID=(String)inputValue;
        List<Student> students = em.createNamedQuery("Student.findBySid").setParameter("sid", ID).getResultList();
        
        if(students==null || students.size()<=0){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "学号不存在!", ""));
        }
        else if(students.get(0).getPassword()!=null ){
             throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "该用户已经注册过了!", ""));
        }
        else if(!students.get(0).getSname().equals(name))
        {
             throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "姓名与学号不匹配!", ""));
        }
        else{
            this.student=students.get(0);    
        }
     
     }
     
    
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
