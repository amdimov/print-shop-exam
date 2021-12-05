package com.example.transferhall.models.dto;

import com.example.transferhall.models.OrdersEntity;
import com.example.transferhall.models.SellerDetailsEntity;
import com.example.transferhall.models.UsersEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class InvoiceDTO {
    private String invoiceNumber;
    private BigDecimal totalAmount;
    private Boolean addVat;
    private Boolean mergedOrders;
    private Boolean vatConfirmed;
    private LocalDate issuedDate;
    private String printMethodDescription;
    private Boolean payed;
    private BigDecimal shippingCost;
    private String trackingNumber;
    private List<OrderDetailsDTO> orders;
    private Long userId;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public InvoiceDTO setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public InvoiceDTO setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public Boolean getAddVat() {
        return addVat;
    }

    public InvoiceDTO setAddVat(Boolean addVat) {
        this.addVat = addVat;
        return this;
    }

    public Boolean getMergedOrders() {
        return mergedOrders;
    }

    public InvoiceDTO setMergedOrders(Boolean mergedOrders) {
        this.mergedOrders = mergedOrders;
        return this;
    }

    public Boolean getVatConfirmed() {
        return vatConfirmed;
    }

    public InvoiceDTO setVatConfirmed(Boolean vatConfirmed) {
        this.vatConfirmed = vatConfirmed;
        return this;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public InvoiceDTO setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
        return this;
    }

    public Boolean getPayed() {
        return payed;
    }

    public InvoiceDTO setPayed(Boolean payed) {
        this.payed = payed;
        return this;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public InvoiceDTO setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
        return this;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public InvoiceDTO setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
        return this;
    }

    public String getPrintMethodDescription() {
        return printMethodDescription;
    }

    public InvoiceDTO setPrintMethodDescription(String printMethodDescription) {
        this.printMethodDescription = printMethodDescription;
        return this;
    }

    public List<OrderDetailsDTO> getOrders() {
        return orders;
    }

    public InvoiceDTO setOrders(List<OrderDetailsDTO> orders) {
        this.orders = orders;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public InvoiceDTO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
