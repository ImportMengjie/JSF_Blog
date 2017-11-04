/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student.bean;

import com.student.entity.Discuss;
import com.student.entity.News;
import com.student.entity.Student;
import com.student.entity.Type;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

/**
 *
 * @author Mengjie
 */
@Named(value = "userBean")
//@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    /**
     * Creates a new instance of UserBean
     */
    
    private String studentID;
    private String password;
    private Student student = null;
    private String discuss;
    
    private String selectType;
    private String Text;
    private String header;
    
//    @ManagedProperty(value="#{appBean}")
//    private AppBean appBean;
    
    @PersistenceContext
    private EntityManager em;
//    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("WebsitePU");
//    private EntityManager em = emf.createEntityManager();
    
    private HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    
    public UserBean() {
//        if(appBean==null)
//            System.out.println("appbean==null");
    }
    
    public void logout(ActionEvent actionEvent){
        this.student=null;
    }
    
     @Transactional
    public void addEssay(ActionEvent actionEvent){
        int typeID=Integer.parseInt(selectType);
        Type type = (Type)em.createNamedQuery("Type.findByTid").setParameter("tid", typeID).getSingleResult();
        News news=new News();
        news.setNcontext(Text);
        news.setNdate(new Date());
        news.setNtitle(header);
        news.setTid(type);
        news.setStudentSid(student);
        
        em.persist(news);
        em.flush();
    
    }
    
    
     @Transactional
    public void hanldComment(ActionEvent actionEvent){
        //System.out.println(request);
        //FacesContext.getCurrentInstance().getExternalContext().
        request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String eID = request.getParameter("essay");
        
        if(student!=null && eID!=null)
        {
            int essay = Integer.parseInt(eID);
            News news = (News)em.createNamedQuery("News.findByNid").setParameter("nid", essay).getSingleResult();
            Discuss d=new Discuss();
            d.setDcontext(discuss);
            d.setDdate(new Date());
            d.setNewsNid(news);
            d.setStudentSid(this.student);
            em.persist(d);
            em.flush();
        }
    }
        
    
    
    
    public void validateLogin(FacesContext context, UIComponent component,  Object inputValue){
          String password = (String)inputValue;
          List<Student> students = em.createNamedQuery("Student.findBySid").setParameter("sid", studentID).getResultList();
          if(students==null||students.size()<=0){
              FacesContext.getCurrentInstance().addMessage("form_login:ID", new FacesMessage(FacesMessage.SEVERITY_ERROR, "学号不存在!", ""));
              throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"",""));
          }
          if(students.get(0).getPassword()==null)
              throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "学号未注册!", ""));
          if(!students.get(0).getPassword().equals(password)){
              throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "密码不正确!", ""));
          }
          this.student=students.get(0);
      }
    
    
      
      
      
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String StudentID) {
        this.studentID = StudentID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getDiscuss() {
        return discuss;
    }

    public void setDiscuss(String discuss) {
        this.discuss = discuss;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }



    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

//    public AppBean getAppBean() {
//        return appBean;
//    }
//
//    public void setAppBean(AppBean appBean) {
//        this.appBean = appBean;
//    }
    
}
