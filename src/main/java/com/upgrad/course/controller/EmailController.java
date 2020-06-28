package com.upgrad.course.controller;

import com.upgrad.course.dto.EmailDto;
import com.upgrad.course.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    // TODO: Define API Endpoint for adding a new email
    //  Use appropriate annotation to receive the request body as a method argument
    @PostMapping("/emails")
    public ResponseEntity<Long> createEmail(@RequestBody EmailDto emailDto) {

        // TODO: Call service method to add a new email
        //  Return 201 CREATED response
        emailService.addEmail(emailDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // TODO: Define API Endpoint for getting all the emails
    @GetMapping("/emails")
    public ResponseEntity<List<EmailDto>> getEmails() {

        // TODO: Call service method to get all emails
        //  Return 200 OK response with List of Emails as body
        List<EmailDto> emails = emailService.getEmails();
        return ResponseEntity.status(HttpStatus.OK)
                .body(emails);
    }
}
