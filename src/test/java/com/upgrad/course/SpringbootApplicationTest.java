package com.upgrad.course;

import com.upgrad.course.entity.Address;
import com.upgrad.course.repository.AddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringbootApplicationTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AddressRepository addressRepository;

    @BeforeEach
    void clean() {
        addressRepository.deleteAll();
    }

    @Test
    public void shouldReturn200WhenCityIsUpdatedSuccessfully() {
        Address address = new Address("58", "High Street", "Mumbai", "Maharashtra", 400001);
        Address savedAddress = addressRepository.save(address);

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<Void> response = testRestTemplate.exchange(baseUrl + "/addresses/{addressId}", HttpMethod.DELETE, null, Void.class, savedAddress.getId());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(Optional.empty(), addressRepository.findById(savedAddress.getId()));
    }


    @Test
    public void shouldReturnFailureIfAddressDoesNotExist() {
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        ResponseEntity<Void> response = testRestTemplate.exchange(baseUrl + "/addresses/{addressId}", HttpMethod.DELETE, null, Void.class, 123L);

        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
}
