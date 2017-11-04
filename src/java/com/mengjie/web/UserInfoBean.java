/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mengjie.web;

import com.mengjie.entity.User;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Mengjie
 */
@Named(value = "userInfoBean")
@SessionScoped
public class UserInfoBean implements Serializable {
    
    private User user; 
    private String password;
    private String mail;


    public UserInfoBean() {
    
    }
    
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    
}
