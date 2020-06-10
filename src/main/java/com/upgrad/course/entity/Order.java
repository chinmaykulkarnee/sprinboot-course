package com.upgrad.course.entity;

import javax.persistence.*;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String userId;

    private Double amount;

    public Order() {
    }

    public Order(String userId, Double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String name) {
        this.userId = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double email) {
        this.amount = email;
    }
}
