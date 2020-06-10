package com.upgrad.course.repository;

import com.upgrad.course.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// TODO: Mark this class a repository using using correct annotation
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
