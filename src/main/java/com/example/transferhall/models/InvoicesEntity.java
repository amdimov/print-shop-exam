package com.example.transferhall.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "invoices")
public class InvoicesEntity extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String invoiceNumber;

    @ManyToOne
    private SellerDetailsEntity sellerInformation;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "add_vat")
    private Boolean addVat;

    @Column
    private Boolean mergedOrders;

    @Column
    private String printMethodDescription;

    @Column
    private Boolean vatConfirmed;

    @Column(name = "issued_date")
    private LocalDate issuedDate;

    @Column(nullable = false)
    private Boolean payed = false;

    @Column
    private BigDecimal shippingCost;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @OneToMany(mappedBy = "invoices")
    private List<OrdersEntity> orders;

    @ManyToOne
    private UsersEntity users;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public InvoicesEntity setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public Boolean getAddVat() {
        return addVat;
    }

    public InvoicesEntity setAddVat(Boolean addVat) {
        this.addVat = addVat;
        return this;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public InvoicesEntity setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
        return this;
    }

    public List<OrdersEntity> getOrders() {
        return orders;
    }

    public InvoicesEntity setOrders(List<OrdersEntity> orders) {
        this.orders = orders;
        return this;
    }

    public UsersEntity getUsers() {
        return users;
    }

    public InvoicesEntity setUsers(UsersEntity users) {
        this.users = users;
        return this;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public InvoicesEntity setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public SellerDetailsEntity getSellerInformation() {
        return sellerInformation;
    }

    public InvoicesEntity setSellerInformation(SellerDetailsEntity sellerInformation) {
        this.sellerInformation = sellerInformation;
        return this;
    }

    public Boolean getPayed() {
        return payed;
    }

    public InvoicesEntity setPayed(Boolean payed) {
        this.payed = payed;
        return this;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public InvoicesEntity setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
        return this;
    }

    public Boolean getVatConfirmed() {
        return vatConfirmed;
    }

    public InvoicesEntity setVatConfirmed(Boolean vatConfirmed) {
        this.vatConfirmed = vatConfirmed;
        return this;
    }

    public Boolean getMergedOrders() {
        return mergedOrders;
    }

    public InvoicesEntity setMergedOrders(Boolean mergedOrders) {
        this.mergedOrders = mergedOrders;
        return this;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public InvoicesEntity setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
        return this;
    }

    public String getPrintMethodDescription() {
        return printMethodDescription;
    }

    public InvoicesEntity setPrintMethodDescription(String printMethodDescription) {
        this.printMethodDescription = printMethodDescription;
        return this;
    }
}
