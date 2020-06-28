package com.upgrad.course;

import com.upgrad.course.dto.AddressDto;
import com.upgrad.course.entity.AddressEntity;
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

import java.util.Optional;


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
        AddressEntity addressEntity = new AddressEntity("58", "High Street", "Mumbai", "Maharashtra", 400001);
        AddressEntity savedAddressEntity = addressRepository.save(addressEntity);

        AddressDto cityUpdateRequest = new AddressDto();
        cityUpdateRequest.setCity("Pune");

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AddressDto> entity = new HttpEntity<>(cityUpdateRequest, headers);
        ResponseEntity<Void> response = patchRestTemplate.exchange(baseUrl + "/addresses/{addressId}", HttpMethod.PATCH, entity, Void.class, savedAddressEntity.getRegistrationNumber());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Pune", addressRepository.findById(savedAddressEntity.getRegistrationNumber()).get().getCity());
    }


    @Test
    public void shouldReturnFailureIfAddressDoesNotExist() {
        AddressEntity addressEntity = new AddressEntity("58", "High Street", "Mumbai", "Maharashtra", 400001);
        AddressEntity savedAddressEntity = addressRepository.save(addressEntity);

        AddressDto cityUpdateRequest = new AddressDto();
        cityUpdateRequest.setCity("Pune");

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AddressDto> entity = new HttpEntity<>(cityUpdateRequest, headers);
        ResponseEntity<Void> response = patchRestTemplate.exchange(baseUrl + "/addresses/{addressId}", HttpMethod.PATCH, entity, Void.class, 123L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void shouldReturn200WhenAddressIsUpdatedSuccessfully() {
        AddressEntity addressEntity = new AddressEntity("58", "High Street", "Mumbai", "Maharashtra", 400001);
        AddressEntity savedAddressEntity = addressRepository.save(addressEntity);

        AddressDto addressDto = new AddressDto("589", "High Street 2", "Bengaluru", "Karnataka", 56007);

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AddressDto> entity = new HttpEntity<>(addressDto, headers);
        ResponseEntity<Void> response = testRestTemplate.exchange(baseUrl + "/addresses/{addressId}", HttpMethod.PUT, entity, Void.class, savedAddressEntity.getRegistrationNumber());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Optional<AddressEntity> mayBeUpdatedAddress = addressRepository.findById(savedAddressEntity.getRegistrationNumber());
        Assertions.assertTrue(mayBeUpdatedAddress.isPresent());
        AddressEntity updatedAddress = mayBeUpdatedAddress.get();
        Assertions.assertEquals("589", updatedAddress.getHouseNumber());
        Assertions.assertEquals("High Street 2", updatedAddress.getStreetName());
        Assertions.assertEquals("Bengaluru", updatedAddress.getCity());
        Assertions.assertEquals("Karnataka", updatedAddress.getState());
        Assertions.assertEquals(56007, updatedAddress.getPinCode());
        Assertions.assertEquals(savedAddressEntity.getRegistrationNumber(), updatedAddress.getRegistrationNumber());
    }

    @Test
    public void shouldReturnFailureInUpdateIfAddressDoesNotExist() {
        AddressEntity addressEntity = new AddressEntity("58", "High Street", "Mumbai", "Maharashtra", 400001);
        AddressEntity savedAddressEntity = addressRepository.save(addressEntity);

        AddressDto addressDto = new AddressDto("589", "High Street 2", "Bengaluru", "Karnataka", 56007);

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AddressDto> entity = new HttpEntity<>(addressDto, headers);
        ResponseEntity<Void> response = testRestTemplate.exchange(baseUrl + "/addresses/{addressId}", HttpMethod.PUT, entity, Void.class, 123L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
