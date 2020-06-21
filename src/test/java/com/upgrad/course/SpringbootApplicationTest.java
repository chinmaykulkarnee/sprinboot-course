package com.upgrad.course;

import com.upgrad.course.entity.Address;
import com.upgrad.course.repository.AddressRepository;
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
    private TestRestTemplate testRestTemplate;
    private RestTemplate patchRestTemplate;

    @Autowired
    private AddressRepository addressRepository;

    @BeforeEach
    void clean() {
        addressRepository.deleteAll();
        this.patchRestTemplate = testRestTemplate.getRestTemplate();
        HttpClient httpClient = HttpClientBuilder.create().build();
        this.patchRestTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
    }

    @Test
    public void shouldReturn200WhenCityIsUpdatedSuccessfully() {
        Address address = new Address("58", "High Street", "Mumbai", "Maharashtra", 400001);
        Address savedAddress = addressRepository.save(address);

        Address cityUpdateRequest = new Address();
        cityUpdateRequest.setCity("Pune");

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Address> entity = new HttpEntity<>(cityUpdateRequest, headers);
        ResponseEntity<Void> response = patchRestTemplate.exchange(baseUrl + "/addresses/{addressId}", HttpMethod.PATCH, entity, Void.class, savedAddress.getId());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Pune", addressRepository.findById(savedAddress.getId()).get().getCity());
    }


    @Test
    public void shouldReturnFailureIfAddressDoesNotExist() {
        Address address = new Address("58", "High Street", "Mumbai", "Maharashtra", 400001);

        Address cityUpdateRequest = new Address();
        cityUpdateRequest.setCity("Pune");

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Address> entity = new HttpEntity<>(address, headers);
        ResponseEntity<Void> response = patchRestTemplate.exchange(baseUrl + "/addresses/{addressId}", HttpMethod.PATCH, entity, Void.class, 123L);

        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
}
