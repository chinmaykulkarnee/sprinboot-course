package com.upgrad.course;

import com.upgrad.course.model.Product;
import com.upgrad.course.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootApplicationTest {

	@Autowired
	private ProductService productService;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldGetProductsUsingProductService() {
		List<Product> products = productService.getProducts();
		Assertions.assertEquals(1, products.size());
		Product product = products.get(0);
		Assertions.assertEquals("first product", product.getName());
		Assertions.assertEquals(100, product.getPrice());
	}
}
