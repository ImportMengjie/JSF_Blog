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
@Table(name = "discuss")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Discuss.findAll", query = "SELECT d FROM Discuss d")
    , @NamedQuery(name = "Discuss.findByDid", query = "SELECT d FROM Discuss d WHERE d.did = :did")
    , @NamedQuery(name = "Discuss.findByDcontext", query = "SELECT d FROM Discuss d WHERE d.dcontext = :dcontext")
    , @NamedQuery(name = "Discuss.findByDdate", query = "SELECT d FROM Discuss d WHERE d.ddate = :ddate")})
public class Discuss implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Did")
    private Integer did;
    @Size(max = 512)
    @Column(name = "Dcontext")
    private String dcontext;
    @Column(name = "Ddate")
    @Temporal(TemporalType.DATE)
    private Date ddate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discussDid")
    private Collection<Replay> replayCollection;
    @JoinColumn(name = "news_Nid", referencedColumnName = "Nid")
    @ManyToOne(optional = false)
    private News newsNid;
    @JoinColumn(name = "student_Sid", referencedColumnName = "Sid")
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},optional = false)
    private Student studentSid;

    public Discuss() {
    }

    public Discuss(Integer did) {
        this.did = did;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDcontext() {
        return dcontext;
    }

    public void setDcontext(String dcontext) {
        this.dcontext = dcontext;
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }

    @XmlTransient
    public Collection<Replay> getReplayCollection() {
        return replayCollection;
    }

    public void setReplayCollection(Collection<Replay> replayCollection) {
        this.replayCollection = replayCollection;
    }

    public News getNewsNid() {
        return newsNid;
    }

    public void setNewsNid(News newsNid) {
        this.newsNid = newsNid;
    }

    public Student getStudentSid() {
        return studentSid;
    }

    public void setStudentSid(Student studentSid) {
        this.studentSid = studentSid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (did != null ? did.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Discuss)) {
            return false;
        }
        Discuss other = (Discuss) object;
        if ((this.did == null && other.did != null) || (this.did != null && !this.did.equals(other.did))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.student.entity.Discuss[ did=" + did + " ]";
    }
    
    public String getDateString(){
        if(this.ddate==null)
            return "no date";
        else
            return this.ddate.toLocaleString().substring(0, 9);
    }
    
}
