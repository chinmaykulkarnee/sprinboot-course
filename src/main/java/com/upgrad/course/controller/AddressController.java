package com.upgrad.course.controller;

import com.upgrad.course.dto.AddressDto;
import com.upgrad.course.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // TODO: Define PUT API Endpoint for updating city in the address
    //  Use appropriate annotation to receive the path variable "registrationNumber" as a method argument
    //  Use appropriate annotation to receive the request body as a AddressDto method argument
    @PutMapping("/addresses/{registrationNumber}")
    public ResponseEntity updateAddress(@PathVariable Long registrationNumber, @RequestBody AddressDto request) {

        // TODO: Call service method to update the address
        //  Return 200 OK response when service returns true as status
        //  Return 404 NOT_FOUND response when service returns false as status
        boolean status = addressService.updateAddress(registrationNumber, request);
        if (status) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // TODO: Define PATCH API Endpoint for updating city in the address
    //  Use appropriate annotation to receive the path variable "registrationNumber" as a method argument
    //  Use appropriate annotation to receive the request body as a AddressDto method argument
    @PatchMapping("/addresses/{registrationNumber}")
    public ResponseEntity updateCity(@PathVariable Long registrationNumber, @RequestBody AddressDto request) {

        // TODO: Call service method to update the address
        //  Return 200 OK response when service returns true as status
        //  Return 404 NOT_FOUND response when service returns false as status
        boolean status = addressService.updateAddressCity(registrationNumber, request.getCity());
        if (status) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
