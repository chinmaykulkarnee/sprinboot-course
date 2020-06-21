package com.upgrad.course.repository;

import com.upgrad.course.entity.Address;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

    @Modifying
    @Query("update Address a set a.city = ?1 where a.id = ?2")
    void updateCity(String city, Long addressId);
}
