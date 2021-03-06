package com.upgrad.course.service;

import com.upgrad.course.model.Product;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

// TODO: Mark this class a service using using correct annotation
@Service
public class ProductService {

    private List<Product> products;

    public ProductService() {
        this.products = Collections.singletonList(new Product("first product", 100));
    }

    public List<Product> getProducts() {
        return this.products;
    }
}
