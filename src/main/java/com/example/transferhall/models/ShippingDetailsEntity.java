package com.example.transferhall.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "shipping_details")
@Entity
public class ShippingDetailsEntity extends BaseEntity {
    @Column
    private String fullname;
    @Column
    private String deliveryAddress;
    @Column
    private String postCode;
    @Column
    private String city;
    @Column
    private String country;
    @Column
    private String phoneNumber;
    @OneToOne(mappedBy = "shippingDetails")
    private UsersEntity usersEntity;
    @Column
    private LocalDateTime created;

    public String getFullname() {
        return fullname;
    }

    public ShippingDetailsEntity setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public ShippingDetailsEntity setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }


    public String getPostCode() {
        return postCode;
    }

    public ShippingDetailsEntity setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ShippingDetailsEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public ShippingDetailsEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ShippingDetailsEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UsersEntity getUsersEntity() {
        return usersEntity;
    }

    public ShippingDetailsEntity setUsersEntity(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public ShippingDetailsEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }
}