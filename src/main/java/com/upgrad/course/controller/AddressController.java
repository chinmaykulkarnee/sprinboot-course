package com.upgrad.course.controller;

import com.upgrad.course.entity.Address;
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

    // TODO: Define API Endpoint for updating an address
    //  Use PutMapping annotation
    //  Use appropriate annotation to receive the path variable "addressId" as a method argument
    //  Use appropriate annotation to receive the request body as a method argument
    @PatchMapping("/addresses/{addressId}")
    public ResponseEntity updateAddress(@PathVariable Long addressId, @RequestBody Address request) {

        // TODO: Call service method to update the address
        //  Return 200 OK response when service returns true as status
        //  Return 403 FORBIDDEN response when service returns false as status
        boolean status = addressService.updateAddressCity(addressId, request.getCity());
        if (status) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
