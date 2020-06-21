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
    public boolean updateAddress(Long addressId, Address address) {
        if (addressRepository.findById(addressId).isPresent()) {

            // TODO: Use repository method to update the address when address already exists
            addressRepository.update(
                    address.getHouseNumber(),
                    address.getStreetName(),
                    address.getCity(),
                    address.getState(),
                    address.getPinCode(),
                    addressId
            );
            return true;
        }
        return false;
    }
}
