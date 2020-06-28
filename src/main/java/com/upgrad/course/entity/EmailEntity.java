package com.upgrad.course.entity;

import javax.persistence.*;

@Entity
public class EmailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String toEmail;
    private String fromEmail;
    private String subject;
    private String body;

    public EmailEntity() {
    }

    public EmailEntity(String toEmail, String fromEmail, String subject, String body) {
        this.toEmail = toEmail;
        this.fromEmail = fromEmail;
        this.subject = subject;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String to) {
        this.toEmail = to;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String from) {
        this.fromEmail = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
