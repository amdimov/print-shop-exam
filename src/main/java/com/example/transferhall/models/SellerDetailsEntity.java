package com.example.transferhall.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class SellerDetailsEntity extends BaseEntity{
    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "vat_number")
    private String vatNumber;

    @Column(name = "company_address")
    private String companyAdress;

    @Column(name = "name_issued_to")
    private String nameIssuedTo;

    @Column(name = "bank_name_iban")
    private String bankNameAndIBAN;

    @Column(name = "additional_information")
    private String additionalInformation;

    public String getCompanyName() {
        return companyName;
    }

    public SellerDetailsEntity setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public SellerDetailsEntity setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
        return this;
    }

    public String getCompanyAdress() {
        return companyAdress;
    }

    public SellerDetailsEntity setCompanyAdress(String companyAdress) {
        this.companyAdress = companyAdress;
        return this;
    }

    public String getNameIssuedTo() {
        return nameIssuedTo;
    }

    public SellerDetailsEntity setNameIssuedTo(String nameIssuedTo) {
        this.nameIssuedTo = nameIssuedTo;
        return this;
    }

    public String getBankNameAndIBAN() {
        return bankNameAndIBAN;
    }

    public SellerDetailsEntity setBankNameAndIBAN(String bankNameAndIBAN) {
        this.bankNameAndIBAN = bankNameAndIBAN;
        return this;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public SellerDetailsEntity setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }
}
