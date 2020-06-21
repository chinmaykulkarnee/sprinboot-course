package com.upgrad.course.repository;

import com.upgrad.course.entity.Address;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

    @Modifying
    @Query("update Address a set a.houseNumber = ?1, a.streetName = ?2, a.city = ?3, a.state = ?4, a.pinCode = ?5 where a.id = ?6")
    void update(String houseNumber, String streetName, String city, String state, int pinCode, Long addressId);
}
