/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.pojo;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ThienTu
 */

@Entity
@Table(name = "interaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Interaction.findAll", query = "SELECT i FROM Interaction i"),
    @NamedQuery(name = "Interaction.findById", query = "SELECT i FROM Interaction i WHERE i.id = :id"),
    @NamedQuery(name = "Interaction.findByUserIdAndActivityId", query = "SELECT i FROM Interaction i WHERE i.userId.id = :userId AND i.activityId.id = :activityId"),
    @NamedQuery(name = "Interaction.findByUserIdAndCommentId", query = "SELECT i FROM Interaction i WHERE i.userId.id = :userId AND i.commentId.id = :commentId"),
    @NamedQuery(name = "Interaction.findByCreatedAt", query = "SELECT i FROM Interaction i WHERE i.createdAt = :createdAt")})
public class Interaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    @ManyToOne
    private Activity activityId;
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    @ManyToOne
    private Comment commentId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public Interaction() {
    }

    public Interaction(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Activity getActivityId() {
        return activityId;
    }

    public void setActivityId(Activity activityId) {
        this.activityId = activityId;
    }

    public Comment getCommentId() {
        return commentId;
    }

    public void setCommentId(Comment commentId) {
        this.commentId = commentId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Interaction)) {
            return false;
        }
        Interaction other = (Interaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dtt.pojo.Interaction[ id=" + id + " ]";
    }
    
}
