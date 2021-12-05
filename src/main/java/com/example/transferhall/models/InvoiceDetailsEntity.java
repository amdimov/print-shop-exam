package com.example.transferhall.models;

import com.example.transferhall.models.enums.CurrencyEnum;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "invoice_details")
@Entity
public class InvoiceDetailsEntity extends BaseEntity{

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "vat_number")
    private String vatNumber;

    @Column(name = "country")
    private String country;

    @Column(name = "company_address")
    private String companyAdress;

    @Column(name = "name_issued_to")
    private String nameIssuedTo;

    @Enumerated(EnumType.ORDINAL)
    private CurrencyEnum currency;

    @Column
    private LocalDateTime created;

    @Column
    private String note;

    @Column
    private Boolean verified;

    @OneToOne(mappedBy = "invoiceDetails")
    private UsersEntity users;

    public String getCompanyName() {
        return companyName;
    }

    public InvoiceDetailsEntity setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public InvoiceDetailsEntity setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public InvoiceDetailsEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCompanyAdress() {
        return companyAdress;
    }

    public InvoiceDetailsEntity setCompanyAdress(String companyAdress) {
        this.companyAdress = companyAdress;
        return this;
    }

    public String getNameIssuedTo() {
        return nameIssuedTo;
    }

    public InvoiceDetailsEntity setNameIssuedTo(String nameIssuedTo) {
        this.nameIssuedTo = nameIssuedTo;
        return this;
    }

    public UsersEntity getUsers() {
        return users;
    }

    public InvoiceDetailsEntity setUsers(UsersEntity users) {
        this.users = users;
        return this;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public InvoiceDetailsEntity setCurrency(CurrencyEnum currency) {
        this.currency = currency;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public InvoiceDetailsEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public String getNote() {
        return note;
    }

    public InvoiceDetailsEntity setNote(String note) {
        this.note = note;
        return this;
    }

    public Boolean getVerified() {
        return verified;
    }

    public InvoiceDetailsEntity setVerified(Boolean verified) {
        this.verified = verified;
        return this;
    }
}