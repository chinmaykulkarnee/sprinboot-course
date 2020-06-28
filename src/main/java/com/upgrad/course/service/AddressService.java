package com.upgrad.course.service;

import com.upgrad.course.dto.AddressDto;

public interface AddressService {
    boolean updateAddress(Long registrationNumber, AddressDto addressDto);

    boolean updateAddressCity(Long registrationNumber, String city);
}
