package com.upgrad.course.controller;

import com.upgrad.course.entity.Order;
import com.upgrad.course.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/api/v1/orders/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
        Optional<Order> mayBeOrder = orderService.getOrderByOrderId(Integer.parseInt(orderId));
        return mayBeOrder
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
