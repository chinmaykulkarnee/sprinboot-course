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
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringbootApplicationTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    private AddressRepository addressRepository;

    @BeforeEach
    void clean() {
        addressRepository.deleteAll();
    }

    @Test
    public void shouldReturn200WhenAddressIsUpdatedSuccessfully() {
        Address address = new Address("58", "High Street", "Mumbai", "Maharashtra", 400001);
        Address savedAddress = addressRepository.save(address);

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Address> entity = new HttpEntity<>(address, headers);
        ResponseEntity<Void> response = testRestTemplate.exchange(baseUrl + "/addresses/{addressId}", HttpMethod.PUT, entity, Void.class, savedAddress.getId());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldReturn200EvenWhenAddressIsUpdatedMultipleTimes() {
        Address address = new Address("58", "High Street", "Mumbai", "Maharashtra", 400001);
        Address savedAddress = addressRepository.save(address);

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Address> entity = new HttpEntity<>(address, headers);

        ResponseEntity<Void> response1 = testRestTemplate.exchange(baseUrl + "/addresses/{addressId}", HttpMethod.PUT, entity, Void.class, savedAddress.getId());
        Assertions.assertEquals(HttpStatus.OK, response1.getStatusCode());
        ResponseEntity<Void> response2 = testRestTemplate.exchange(baseUrl + "/addresses/{addressId}", HttpMethod.PUT, entity, Void.class, savedAddress.getId());
        Assertions.assertEquals(HttpStatus.OK, response2.getStatusCode());
    }

    @Test
    public void shouldReturnFailureIfAddressDoesNotExist() {
        Address address = new Address("58", "High Street", "Mumbai", "Maharashtra", 400001);

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Address> entity = new HttpEntity<>(address, headers);
        ResponseEntity<Void> response = testRestTemplate.exchange(baseUrl + "/addresses/{addressId}", HttpMethod.PUT, entity, Void.class, 123L);

        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
}
