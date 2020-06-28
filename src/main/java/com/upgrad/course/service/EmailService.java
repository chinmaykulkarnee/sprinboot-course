package com.upgrad.course.service;

import com.upgrad.course.dto.EmailDto;

import java.util.List;

public interface EmailService {

    void addEmail(EmailDto emailDto);

    List<EmailDto> getEmails();
}
