/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student.bean;

import com.student.entity.Discuss;
import com.student.entity.News;
import com.student.entity.Replay;
import com.student.entity.Student;
import com.student.entity.Type;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.primefaces.context.ApplicationContext;

/**
 *
 * @author Mengjie
 */

@ManagedBean(eager=true)
@ApplicationScoped
public class AppBean implements Serializable{

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }

    public List<News> getNews() {
        news = em.createNamedQuery("News.findAll").getResultList();
        //em.refresh(news);
        for(Type type:typeList){
            em.refresh(type);
            pairs.add(new Pair(type,new ArrayList<>(type.getNewsCollection())));
        }
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public List<Pair> getPairs() {
        for(Pair p:pairs){
            em.refresh(p.type);
        }
        return pairs;
    }

    public void setPairs(List<Pair> pairs) {
        this.pairs = pairs;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * Creates a new instance of AppBean
     */
    public static class Pair{
        private Type type;
        private List<News> news;
        public Pair(Type type,List<News> news){
            this.type=type;
            this.news=news;
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public List<News> getNews() {
            return news;
        }

        public void setNews(List<News> news) {
            this.news = news;
        }
        
    }
    
    public static class PairDiscuss{
        private Discuss discuss;
        private List<Replay> replays;
        private Student student; 
        public PairDiscuss(Discuss discuss,List<Replay> replays){
            this.discuss=discuss;
            this.replays=replays;
            this.student=discuss.getStudentSid();
        }

        public Discuss getDiscuss() {
            return discuss;
        }

        public void setDiscuss(Discuss discuss) {
            this.discuss = discuss;
        }

        public List<Replay> getReplays() {
            return replays;
        }

        public void setReplays(List<Replay> replays) {
            this.replays = replays;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }
        
    }
    
    private List<Type> typeList;
    private List<News> news;
    private List<Pair> pairs = new ArrayList<>();
    
    
    
    
    //@PersistenceContext
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("WebsitePU");
    private EntityManager em = emf.createEntityManager();
   
    
    
    
    public AppBean() {
        em.setFlushMode(FlushModeType.AUTO);
        update();
        
    }
    
    @Transactional
    public void update(){
//        emf=Persistence.createEntityManagerFactory("WebsitePU");
//        em=emf.createEntityManager();
//   	//em.getTransaction().begin();
        typeList = em.createNamedQuery("Type.findAll").getResultList();
       
        news = em.createNamedQuery("News.findAll").getResultList();
        
        for(Type type:typeList){
            pairs.add(new Pair(type,new ArrayList<>(type.getNewsCollection())));
        }
        
    }
    
    
    
    @Transactional
    public List<PairDiscuss> getPairDiscusses(int essayID){
        List<PairDiscuss> pairDiscusses=new ArrayList<>();
        
        Query query=em.createNamedQuery("News.findByNid").setParameter("nid",essayID).setHint("javax.persistence.CacheRetrieveMode", CacheRetrieveMode.BYPASS);
        News essay = (News)query.getSingleResult();  
        em.refresh(essay);
        for(Discuss d:essay.getDiscussCollection()){
            pairDiscusses.add(new PairDiscuss(d, new ArrayList<Replay>(d.getReplayCollection())));
        }  
        
        return  pairDiscusses;
    }
    
    
    @Transactional
    public void setDisscus(Student studentID,News newsID,String context){
        Discuss discuss=new Discuss();
        discuss.setDcontext(context);
        discuss.setDdate(new Date());
        discuss.setNewsNid(newsID);
        discuss.setStudentSid(studentID);
        
        em.persist(discuss);
        
    }
    
    
}
