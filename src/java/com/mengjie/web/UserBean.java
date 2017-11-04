/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mengjie.web;

import com.mengjie.ejb.UserEntity;
import com.mengjie.entity.User;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.mengjie.java.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import org.primefaces.event.FlowEvent;


/**
 *
 * @author Mengjie
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {
    
    @EJB
    UserEntity userEntity;

   
    
    private int id;
    private String mail;
    private String password;
    private String name = "";
    private Date birthday;
    private String sex;
    private int code=0;
    private int userCode;
    private boolean mailChage;
    private User user=new User();
    
    public static ArrayList<String> mailAddr = new ArrayList<>();
    static {
        mailAddr.add("@qq.com");
        mailAddr.add("@163.com");
        mailAddr.add("@hotmail.com");
        mailAddr.add("@msn.cn");
        mailAddr.add("@sohu.com");
        mailAddr.add("@gmail.com");
        mailAddr.add("@yahoo.com.cn");
        Collections.sort(mailAddr);
    }
    
    public UserBean() {
    }
       
    public void handleMail(){
        mailChage=true;
        
    }
    
    public void sendMail(){
        if(mailChage){
              code = (int)(Math.random()*9000+1000);
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"成功","发送给"+mail+",请注意查收"));
              new MailHandle(mail, code, name).start();           
              mailChage=false;
        }
        else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"错误","别瞎点"));
      
    }
//    public void handleCode(){
//        System.out.println("user:"+userCode+"code"+code);
//        if(code==userCode){
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("验证码正确!"));
//        }
//        else{
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("验证码错误!"));
//        }
//    }
    
    public List<String> autoComplete(String query)
    {
        List<String> ret=new ArrayList<String>();
        int atIndex=query.indexOf("@");
        if(atIndex<0)
            for(String t:mailAddr)
                ret.add(query+t);
        else{
            String end = query.substring(atIndex);
            for(String t:mailAddr)
                if(t.contains(end))
                    ret.add(query.substring(0, atIndex)+t); 
        }
        return ret;
    }

   
    public String onFlowProcess(FlowEvent event){
        if(event.getOldStep().equals("register_ok"))
            return event.getOldStep();
        if(event.getOldStep().equals("pw_tab") && event.getNewStep().equals("register_ok"))
        {
            save();
            
        }
       
        return event.getNewStep();
    
    }
    
    public void validateMail(FacesContext context, UIComponent component,  
                     Object inputValue) {  
        String mail = (String)inputValue;
        mail.trim();
        if(!mail.matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$"))
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"邮箱地址不合法!",""));
        List ret = userEntity.getEntityManager().createNamedQuery("User.findByMail").setParameter("mail", mail).getResultList();
        if(ret==null||ret.size()>=1)
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"邮箱已注册!",""));
        context.addMessage(null, new FacesMessage("邮箱合法!"));
    } 
    
    public void validateCode(FacesContext context, UIComponent component,  
                     Object inputValue)
    {
        int code_ = Integer.parseInt((String)inputValue);
        if(code_>=1000)
        {
            if(this.code==code_)
            {
                context.addMessage(null, new FacesMessage("验证码正确!"));
                user.setMail(mail);
            }     
            else
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"验证码错误!",""));

        }
           
    }
    
    public void validatePW(FacesContext context, UIComponent component,  
                     Object inputValue)
    {
        String pw=(String)inputValue;
        if(!this.password.equals(pw)){
           throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"错误!","两次密码不一致!"));
        }
    }
    

     private  void save(){
        this.user.setBirthday(birthday);
        this.user.setName(name);
        //this.user.setMail(mail);
        this.user.setPassword(this.password);
        this.user.setSex(this.sex);
        userEntity.create(user);
//        System.out.println(user.getBirthday());
//        System.out.println(user.getSex());
//        System.out.println(user.getPassword());
//        System.out.println(user.getMail());
     }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getUserCode() {
        if(userCode==0)
            return "";
        return userCode+"";
    }

    public void setUserCode(String userCode) {
        this.userCode = Integer.parseInt(userCode);
    }
    
}
