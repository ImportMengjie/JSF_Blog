/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student.bean;

import com.student.entity.News;
import com.student.entity.Type;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Mengjie
 */
@ManagedBean
@SessionScoped
public class BodyBean implements java.io.Serializable{

    /**
     * Creates a new instance of BodyBean
     */
    @ManagedProperty(value="#{appBean}")
    private AppBean appBean;
    
    private int index;
    
    private String TextDlgHeader;
    private String TextDlg;
    
    private HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    
    public BodyBean() {
        //FacesContext.getCurrentInstance().getAppli
       // System.out.println(appBean.getPairs().size());
    }
    
    public News getNews(){
        String strParame = request.getParameter("essay");
        if(strParame==null)
        {
            News n =new News();
            n.setNtitle("不要妄想找到bug!");
            n.setNcontext("hhhhhhhhhh");
            return n;
        }
            
        int essayID = Integer.parseInt(strParame);
        for(News news:getPairs().getNews()){
            if(news.getNid()==essayID){
                return news;
            }
        }
        return null;
    }
    
    
    public List<AppBean.PairDiscuss> getPairDiscuss(){
        String s =request.getParameter("essay");
        if(s==null)
            return null;
        return appBean.getPairDiscusses(Integer.parseInt(s));
    }
    
    public AppBean.Pair getPairs(){
        index =Integer.parseInt(request.getParameter("index"));
        return appBean.getPairs().get(Integer.parseInt(request.getParameter("index"))-1);
    }
    
    public List<News> getEssayList(){
       return getPairs().getNews();
    }
    
    public List<News> getEssayList(int type){
        return appBean.getPairs().get(type-1).getNews();  
    }
    
//    public List<News> getEssayList(String type){
//        for(Type t:appBean.getTypeList()){
//            if(t.getTname().equals(type))
//                return getEssayList(t.getTid());
//        }
//        return new ArrayList<>();
//    }
    
    
    public void dialogShow(ActionEvent actionEvent){
         
        int essayID = Integer.parseInt(request.getParameter("essay"));
        System.out.println("dialogSHwo"+essayID);
        for(News news:appBean.getNews()){
            if(news.getNid()==essayID){              
                  this.TextDlg=news.getNcontext();
                  this.TextDlgHeader=news.getNtitle();
                  this.TextDlg+="</br></span>Date:"+news.getDateString();
                  break;
            }
                       
        }
//        String s = n!=null?n.getDateString():"";
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, TextDlgHeader,TextDlg+"</br>Time:"+s);
//         
//        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public AppBean getAppBean() {
        return appBean;
    }

    public void setAppBean(AppBean appBean) {
        this.appBean = appBean;
    }

    public String getTextDlgHeader() {
        return TextDlgHeader;
    }

    public void setTextDlgHeader(String TextDlgHeader) {
        this.TextDlgHeader = TextDlgHeader;
    }

    public String getTextDlg() {
        return TextDlg;
    }

    public void setTextDlg(String TextDlg) {
        this.TextDlg = TextDlg;
    }
    
}
