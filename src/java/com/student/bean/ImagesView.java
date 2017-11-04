/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student.bean;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Mengjie
 */
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
 
@ManagedBean
@ApplicationScoped
public class ImagesView {
     
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 3; i++) {
            images.add("bummp" + i + ".jpg");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
}