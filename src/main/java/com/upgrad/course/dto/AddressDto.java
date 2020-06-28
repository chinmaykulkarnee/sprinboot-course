package com.upgrad.course.dto;

import com.upgrad.course.entity.AddressEntity;

public class AddressDto {

    private String houseNumber;

    private String streetName;

    private String city;

    private String state;

    private int pinCode;

    public AddressDto() {
    }

    public AddressDto(String houseNumber, String streetName, String city, String state, int pinCode) {
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
    
    public AddressEntity convertToEntity() {
        return new AddressEntity(this.houseNumber, this.streetName, this.city, this.state, this.pinCode);
    }
}
