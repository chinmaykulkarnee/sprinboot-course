package com.upgrad.course;

import com.upgrad.course.dto.entity.TaskDto;
import com.upgrad.course.entity.Status;
import com.upgrad.course.entity.TaskEntity;
import com.upgrad.course.repository.TaskRepository;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringbootApplicationTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    TestRestTemplate testRestTemplate;
    private RestTemplate patchRestTemplate;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void clean() {
        taskRepository.deleteAll();
        this.patchRestTemplate = testRestTemplate.getRestTemplate();
        HttpClient httpClient = HttpClientBuilder.create().build();
        this.patchRestTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));

    }

    @Test
    void shouldReturn201WhenTaskIsCreatedSuccessfully() {
        TaskDto taskDto = new TaskDto();
        taskDto.setUserId("user1");
        taskDto.setMessage("Study exception handling");

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<Void> response = testRestTemplate.postForEntity(baseUrl + "/tasks", taskDto, Void.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void shouldReturn409WhenPostIsAlreadyPresent() {
        TaskDto taskDto = new TaskDto();
        taskDto.setUserId("user1");
        taskDto.setMessage("Study exception handling");

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<Void> response1 = testRestTemplate.postForEntity(baseUrl + "/tasks", taskDto, Void.class);
        ResponseEntity<Void> response2 = testRestTemplate.postForEntity(baseUrl + "/tasks", taskDto, Void.class);

        Assertions.assertEquals(HttpStatus.CREATED, response1.getStatusCode());
        Assertions.assertEquals(HttpStatus.CONFLICT, response2.getStatusCode());
    }

    @Test
    void shouldReturn200WhenTaskStatusIsUpdatedSuccessfully() {
        TaskEntity taskEntity = new TaskEntity("user1", "Study spring boot");
        TaskEntity savedTask = taskRepository.save(taskEntity);

        TaskDto taskDto = new TaskDto();
        taskDto.setStatus(Status.COMPLETED);

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TaskDto> entity = new HttpEntity<>(taskDto, headers);
        ResponseEntity<Void> response = patchRestTemplate.exchange(baseUrl + "/tasks/{taskId}", HttpMethod.PATCH, entity, Void.class, savedTask.getId());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldReturn403WhenTaskIsAlreadyCompletedAndUpdateFails() {
        TaskEntity taskEntity = new TaskEntity("user1", "Study spring boot");
        taskEntity.setStatus(Status.COMPLETED);
        TaskEntity savedTask = taskRepository.save(taskEntity);

        TaskDto taskDto = new TaskDto();
        taskDto.setStatus(Status.COMPLETED);

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TaskDto> entity = new HttpEntity<>(taskDto, headers);
        ResponseEntity<Void> response = patchRestTemplate.exchange(baseUrl + "/tasks/{taskId}", HttpMethod.PATCH, entity, Void.class, savedTask.getId());

        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void shouldReturn404WhenTaskNotFoundForUpdate() {
        TaskDto taskDto = new TaskDto();
        taskDto.setStatus(Status.COMPLETED);

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TaskDto> entity = new HttpEntity<>(taskDto, headers);
        ResponseEntity<Void> response = patchRestTemplate.exchange(baseUrl + "/tasks/{taskId}", HttpMethod.PATCH, entity, Void.class, 123L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
