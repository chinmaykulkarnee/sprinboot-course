package com.upgrad.course.service;

import com.upgrad.course.dto.AddressDto;
import com.upgrad.course.entity.AddressEntity;
import com.upgrad.course.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public boolean updateAddress(Long registrationNumber, AddressDto addressDto) {
        if (addressRepository.findById(registrationNumber).isPresent()) {

            // TODO: Use repository method to update the address when address already exists
            AddressEntity addressEntity = addressDto.convertToEntity();
            addressEntity.setRegistrationNumber(registrationNumber);
            addressRepository.save(addressEntity);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean updateAddressCity(Long registrationNumber, String city) {
        if (addressRepository.findById(registrationNumber).isPresent()) {

            // TODO: Use repository method to update the city in the address when address already exists
            addressRepository.updateCity(city, registrationNumber);
            return true;
        } else {
            return false;
        }
    }
}
