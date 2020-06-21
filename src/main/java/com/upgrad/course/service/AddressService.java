package com.upgrad.course.service;

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
    public boolean deleteAddress(Long addressId) {
        if (addressRepository.findById(addressId).isPresent()) {

            // TODO: Use repository method to delete the address by addressId when address already exists
            addressRepository.deleteById(addressId);
            return true;
        }
        return false;
    }
}
