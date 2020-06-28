package com.upgrad.course.repository;

import com.upgrad.course.entity.AddressEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

    @Modifying
    @Query("update AddressEntity a set a.city = ?1 where a.registrationNumber = ?2")
    void updateCity(String city, Long addressId);
}
