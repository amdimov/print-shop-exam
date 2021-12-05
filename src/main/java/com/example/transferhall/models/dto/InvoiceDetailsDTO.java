package com.example.transferhall.models.dto;

import com.example.transferhall.models.enums.CurrencyEnum;

import java.time.LocalDateTime;

public class InvoiceDetailsDTO {

    private Long id;
    private String companyName;
    private String vatNumber;
    private String country;
    private String companyAdress;
    private String nameIssuedTo;
    private CurrencyEnum currency;
    private LocalDateTime created;
    private String note;

    public String getCompanyName() {
        return companyName;
    }

    public InvoiceDetailsDTO setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public InvoiceDetailsDTO setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public InvoiceDetailsDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCompanyAdress() {
        return companyAdress;
    }

    public InvoiceDetailsDTO setCompanyAdress(String companyAdress) {
        this.companyAdress = companyAdress;
        return this;
    }

    public String getNameIssuedTo() {
        return nameIssuedTo;
    }

    public InvoiceDetailsDTO setNameIssuedTo(String nameIssuedTo) {
        this.nameIssuedTo = nameIssuedTo;
        return this;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public InvoiceDetailsDTO setCurrency(CurrencyEnum currency) {
        this.currency = currency;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public InvoiceDetailsDTO setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public String getNote() {
        return note;
    }

    public InvoiceDetailsDTO setNote(String note) {
        this.note = note;
        return this;
    }

    public Long getId() {
        return id;
    }

    public InvoiceDetailsDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
