package com.upgrad.course;

import com.upgrad.course.entity.Order;
import com.upgrad.course.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringbootApplicationTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void clean() {
        orderRepository.deleteAll();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void shouldReturnOrderByOrderId() {
        Order savedOrder = orderRepository.save(new Order("userId1", 100.0));
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<Order> response = testRestTemplate.getForEntity(baseUrl + "/orders/" + savedOrder.getId(), Order.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Order order = response.getBody();
        Assertions.assertNotNull(order);
        Assertions.assertEquals(savedOrder.getId(), order.getId());
        Assertions.assertEquals(savedOrder.getUserId(), order.getUserId());
        Assertions.assertEquals(savedOrder.getAmount(), order.getAmount());
    }

    @Test
    void shouldReturn404ResponseWhenOrderNotFoundByOrderId() {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<Order> response = testRestTemplate.getForEntity(baseUrl + "/orders/" + 123, Order.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
