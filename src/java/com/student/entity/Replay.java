/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mengjie
 */
@Entity
@Table(name = "replay")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Replay.findAll", query = "SELECT r FROM Replay r")
    , @NamedQuery(name = "Replay.findByRid", query = "SELECT r FROM Replay r WHERE r.rid = :rid")
    , @NamedQuery(name = "Replay.findByRcontext", query = "SELECT r FROM Replay r WHERE r.rcontext = :rcontext")
    , @NamedQuery(name = "Replay.findByRdate", query = "SELECT r FROM Replay r WHERE r.rdate = :rdate")})
public class Replay implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Rid")
    private Integer rid;
    @Size(max = 512)
    @Column(name = "Rcontext")
    private String rcontext;
    @Column(name = "Rdate")
    @Temporal(TemporalType.DATE)
    private Date rdate;
    @JoinColumn(name = "discuss_Did", referencedColumnName = "Did")
    @ManyToOne(optional = false)
    private Discuss discussDid;
    @JoinColumn(name = "student_Sid", referencedColumnName = "Sid")
    @ManyToOne(optional = false)
    private Student studentSid;

    public Replay() {
    }

    public Replay(Integer rid) {
        this.rid = rid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRcontext() {
        return rcontext;
    }

    public void setRcontext(String rcontext) {
        this.rcontext = rcontext;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    public Discuss getDiscussDid() {
        return discussDid;
    }

    public void setDiscussDid(Discuss discussDid) {
        this.discussDid = discussDid;
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
        hash += (rid != null ? rid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Replay)) {
            return false;
        }
        Replay other = (Replay) object;
        if ((this.rid == null && other.rid != null) || (this.rid != null && !this.rid.equals(other.rid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.student.entity.Replay[ rid=" + rid + " ]";
    }
    
}
