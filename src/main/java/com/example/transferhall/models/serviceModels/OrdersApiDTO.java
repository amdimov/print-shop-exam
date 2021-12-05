package com.example.transferhall.models.serviceModels;

import com.example.transferhall.models.enums.CurrencyEnum;
import com.example.transferhall.models.enums.UnitTypeEnum;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrdersApiDTO {
    private String orderDescription;

    private UnitTypeEnum unitTypeEnum;

    private Integer quantity;

    private CurrencyEnum currency;

    private LocalDateTime created;

    private BigDecimal vatTax;

    private BigDecimal pricePerUnit;

    private BigDecimal totalPrice;

    public String getOrderDescription() {
        return orderDescription;
    }

    public OrdersApiDTO setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
        return this;
    }

    public UnitTypeEnum getUnitTypeEnum() {
        return unitTypeEnum;
    }

    public OrdersApiDTO setUnitTypeEnum(UnitTypeEnum unitTypeEnum) {
        this.unitTypeEnum = unitTypeEnum;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrdersApiDTO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public OrdersApiDTO setCurrency(CurrencyEnum currency) {
        this.currency = currency;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public OrdersApiDTO setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public BigDecimal getVatTax() {
        return vatTax;
    }

    public OrdersApiDTO setVatTax(BigDecimal vatTax) {
        this.vatTax = vatTax;
        return this;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public OrdersApiDTO setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrdersApiDTO setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}
