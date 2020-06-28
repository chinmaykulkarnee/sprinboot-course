package com.upgrad.course.service;

import com.upgrad.course.dto.EmailDto;
import com.upgrad.course.entity.EmailEntity;
import com.upgrad.course.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailServiceImpl implements EmailService {

    private EmailRepository emailRepository;

    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    // TODO: Implement addToEmail method
    //  Convert EmailDto to EmailEntity for saving to the database using method of EmailDto
    //  Use repository method to save the email
    @Transactional
    public void addEmail(EmailDto emailDto) {
        emailRepository.save(emailDto.convertToEntity());
    }

    // TODO: Implement getEmails method
    //  Convert EmailEntity to EmailDto for returning the API response using method of EmailDto
    //  Use repository method to get all the emails
    public List<EmailDto> getEmails() {
        List<EmailEntity> emailEntities = emailRepository.findAll();
        return emailEntities.stream()
                .map(EmailDto::buildFrom)
                .collect(Collectors.toList());
    }
}
