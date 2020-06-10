package com.upgrad.course.service;

import com.upgrad.course.entity.Order;
import com.upgrad.course.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// TODO: Mark this class a service using using correct annotation
@Service
public class OrderService {

    private OrderRepository orderRepository;

    // TODO: Autowire user repository here to get access to its methods
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // TODO: Use repository to get order by id
    public Optional<Order> getOrderByOrderId(Integer orderId) {
        return this.orderRepository.findById(orderId);
    }
}
