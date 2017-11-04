/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mengjie
 */
@Entity
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
    , @NamedQuery(name = "Student.findBySid", query = "SELECT s FROM Student s WHERE s.sid = :sid")
    , @NamedQuery(name = "Student.findBySname", query = "SELECT s FROM Student s WHERE s.sname = :sname")
    , @NamedQuery(name = "Student.findByPassword", query = "SELECT s FROM Student s WHERE s.password = :password")
    , @NamedQuery(name = "Student.findByPosition", query = "SELECT s FROM Student s WHERE s.position = :position")
    , @NamedQuery(name = "Student.findBySaccess", query = "SELECT s FROM Student s WHERE s.saccess = :saccess")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Sid")
    private String sid;
    @Size(max = 45)
    @Column(name = "Sname")
    private String sname;
    @Size(max = 16)
    @Column(name = "password")
    private String password;
    @Size(max = 45)
    @Column(name = "position")
    private String position;
    @Column(name = "Saccess")
    private Integer saccess;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentSid")
    private Collection<News> newsCollection;
    @JoinColumn(name = "Bid", referencedColumnName = "Bid")
    @ManyToOne
    private Branch bid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentSid")
    private Collection<Replay> replayCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentSid")
    private Collection<Discuss> discussCollection;

    public Student() {
    }

    public Student(String sid) {
        this.sid = sid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getSaccess() {
        return saccess;
    }

    public void setSaccess(Integer saccess) {
        this.saccess = saccess;
    }

    @XmlTransient
    public Collection<News> getNewsCollection() {
        return newsCollection;
    }

    public void setNewsCollection(Collection<News> newsCollection) {
        this.newsCollection = newsCollection;
    }

    public Branch getBid() {
        return bid;
    }

    public void setBid(Branch bid) {
        this.bid = bid;
    }

    @XmlTransient
    public Collection<Replay> getReplayCollection() {
        return replayCollection;
    }

    public void setReplayCollection(Collection<Replay> replayCollection) {
        this.replayCollection = replayCollection;
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
        hash += (sid != null ? sid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.sid == null && other.sid != null) || (this.sid != null && !this.sid.equals(other.sid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.student.entity.Student[ sid=" + sid + " ]";
    }
    
}
