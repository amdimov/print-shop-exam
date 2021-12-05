package com.example.transferhall.models.dto;

public class UserDetailsDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String companyName;
    private Integer discountPercent;
    private Boolean isItDelated;
    private InvoiceDetailsDTO invoiceDetails;
    private ShippingDetailsDTO shippingDetails;

    public String getFirstName() {
        return firstName;
    }

    public UserDetailsDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDetailsDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDetailsDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public UserDetailsDTO setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public UserDetailsDTO setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
        return this;
    }

    public Boolean getItDelated() {
        return isItDelated;
    }

    public UserDetailsDTO setItDelated(Boolean itDelated) {
        isItDelated = itDelated;
        return this;
    }

    public InvoiceDetailsDTO getInvoiceDetails() {
        return invoiceDetails;
    }

    public UserDetailsDTO setInvoiceDetails(InvoiceDetailsDTO invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
        return this;
    }

    public ShippingDetailsDTO getShippingDetails() {
        return shippingDetails;
    }

    public UserDetailsDTO setShippingDetails(ShippingDetailsDTO shippingDetails) {
        this.shippingDetails = shippingDetails;
        return this;
    }

    public String getId() {
        return id;
    }

    public UserDetailsDTO setId(String id) {
        this.id = id;
        return this;
    }
}
