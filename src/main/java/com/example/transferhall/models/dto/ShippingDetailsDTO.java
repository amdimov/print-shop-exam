package com.example.transferhall.models.dto;

import javax.persistence.Column;

public class ShippingDetailsDTO {

    private String fullname;

    private String deliveryAddress;

    private String postCode;

    private String city;

    private String country;

    private String phoneNumber;

    public String getFullname() {
        return fullname;
    }

    public ShippingDetailsDTO setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public ShippingDetailsDTO setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public ShippingDetailsDTO setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ShippingDetailsDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public ShippingDetailsDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ShippingDetailsDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
