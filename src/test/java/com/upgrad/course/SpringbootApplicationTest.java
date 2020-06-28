package com.upgrad.course;

import com.upgrad.course.entity.EmailEntity;
import com.upgrad.course.repository.EmailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringbootApplicationTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    private EmailRepository emailRepository;

    @BeforeEach
    void clean() {
        emailRepository.deleteAll();
    }

    @Test
    public void shouldReturn201WhenEmailIsCreatedSuccessfully() {
        EmailEntity emailEntityToSave = new EmailEntity("abc@email.com", "pqr@email.com", "this is subject", "this is body");

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<Void> response = testRestTemplate.postForEntity(baseUrl + "/emails", emailEntityToSave, Void.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(1, emailRepository.findAll().size());
    }

    @Test
    public void shouldReturn200WithListOfEmails() {
        EmailEntity emailEntityToSave1 = new EmailEntity("abc@email.com", "pqr@email.com", "this is subject", "this is body");
        EmailEntity emailEntityToSave2 = new EmailEntity("xyz@email.com", "def@email.com", "this is subject2", "this is body2");
        emailRepository.save(emailEntityToSave1);
        emailRepository.save(emailEntityToSave2);

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<EmailEntity[]> response = testRestTemplate.getForEntity(baseUrl + "/emails", EmailEntity[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<EmailEntity> emailEntities = Arrays.asList(Objects.requireNonNull(response.getBody()));
        Assertions.assertEquals(2, emailEntities.size());
        Assertions.assertEquals(2, emailRepository.findAll().size());
    }

    @Test
    public void shouldReturn200WithEmptyListOfEmailsWhenNoEmailsSaved() {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<EmailEntity[]> response = testRestTemplate.getForEntity(baseUrl + "/emails", EmailEntity[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        List<EmailEntity> emailEntities = Arrays.asList(Objects.requireNonNull(response.getBody()));
        Assertions.assertEquals(0, emailEntities.size());
        Assertions.assertEquals(0, emailRepository.findAll().size());
    }
}
