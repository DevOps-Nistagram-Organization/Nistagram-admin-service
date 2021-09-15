package com.nistagram.administrator.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ReportedPost {
    @GeneratedValue
    @Id
    private Long id;
    @Column
    private Long postId;
    @Column
    private String reportedByUsername;
    @Column
    private Date date;

    public ReportedPost() {
    }

    public ReportedPost(Long id, Long postId, String reportedByUsername, Date date) {
        this.id = id;
        this.postId = postId;
        this.reportedByUsername = reportedByUsername;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getReportedByUsername() {
        return reportedByUsername;
    }

    public void setReportedByUsername(String reportedByUsername) {
        this.reportedByUsername = reportedByUsername;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
