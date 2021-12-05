package com.example.transferhall.models.views;

import com.example.transferhall.models.dto.InvoiceDetailsDTO;
import com.example.transferhall.models.dto.ShippingDetailsDTO;

import java.math.BigDecimal;

public class PendingUsersView {
    private Long id;
    private String companyName;
    private String email;
    private Integer discountPercent;


    public Long getId() {
        return id;
    }

    public PendingUsersView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public PendingUsersView setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public PendingUsersView setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public PendingUsersView setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
        return this;
    }
}
