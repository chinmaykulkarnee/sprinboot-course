package com.upgrad.course.dto;

import com.upgrad.course.entity.EmailEntity;

public class EmailDto {

    private String to;
    private String from;
    private String subject;
    private String body;

    public EmailDto() {
    }

    public EmailDto(String to, String from, String subject, String body) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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

    public EmailEntity convertToEntity() {
        return new EmailEntity(to, from, subject, body);
    }

    public static EmailDto buildFrom(EmailEntity emailEntity) {
        return new EmailDto(emailEntity.getToEmail(), emailEntity.getFromEmail(), emailEntity.getSubject(), emailEntity.getBody());
    }
}
