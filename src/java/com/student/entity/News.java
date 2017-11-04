/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mengjie
 */
@Entity
@Table(name = "news")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "News.findAll", query = "SELECT n FROM News n ORDER BY n.nid")
    , @NamedQuery(name = "News.findByNid", query = "SELECT n FROM News n WHERE n.nid = :nid")
    , @NamedQuery(name = "News.findByNtitle", query = "SELECT n FROM News n WHERE n.ntitle = :ntitle")
    , @NamedQuery(name = "News.findByNdate", query = "SELECT n FROM News n WHERE n.ndate = :ndate")
    , @NamedQuery(name = "News.findByNgrade", query = "SELECT n FROM News n WHERE n.ngrade = :ngrade")})
public class News implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Nid")
    private Integer nid;
    @Size(max = 128)
    @Column(name = "Ntitle")
    private String ntitle;
    @Lob
    @Size(max = 16777215)
    @Column(name = "Ncontext")
    private String ncontext;
    @Column(name = "Ndate")
    @Temporal(TemporalType.DATE)
    private Date ndate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Ngrade")
    private Float ngrade;
    @JoinColumn(name = "student_Sid", referencedColumnName = "Sid")
    @ManyToOne(optional = false)
    private Student studentSid;
    @JoinColumn(name = "Tid", referencedColumnName = "Tid")
    @ManyToOne(optional = false)
    private Type tid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "newsNid")
    //@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, mappedBy = "newsNid")
    private Collection<Discuss> discussCollection;

    public News() {
    }

    public News(Integer nid) {
        this.nid = nid;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public String getNcontext() {
        return ncontext;
    }

    public void setNcontext(String ncontext) {
        this.ncontext = ncontext;
    }

    public Date getNdate() {
        return ndate;
    }
    
    
    public void setNdate(Date ndate) {
        this.ndate = ndate;
    }

    public Float getNgrade() {
        return ngrade;
    }

    public void setNgrade(Float ngrade) {
        this.ngrade = ngrade;
    }

    public Student getStudentSid() {
        return studentSid;
    }

    public void setStudentSid(Student studentSid) {
        this.studentSid = studentSid;
    }

    public Type getTid() {
        return tid;
    }

    public void setTid(Type tid) {
        this.tid = tid;
    }

    @XmlTransient
    public Collection<Discuss> getDiscussCollection() {
        return discussCollection;
    }

    public void setDiscussCollection(Collection<Discuss> discussCollection) {
        this.discussCollection = discussCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nid != null ? nid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof News)) {
            return false;
        }
        News other = (News) object;
        if ((this.nid == null && other.nid != null) || (this.nid != null && !this.nid.equals(other.nid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.student.entity.News[ nid=" + nid + " ]";
    }
    
    public String getDateString(){
        if(ndate!=null)
            return this.ndate.toLocaleString().substring(0, 9);
        return "no data";
    }
    
}
