package com.upgrad.course.service;

import com.upgrad.course.entity.Address;
import com.upgrad.course.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public boolean updateAddressCity(Long addressId, String city) {
        if (addressRepository.findById(addressId).isPresent()) {

            // TODO: Use repository method to update the address when address already exists
            addressRepository.updateCity(city, addressId);
            return true;
        }
        return false;
    }
}
