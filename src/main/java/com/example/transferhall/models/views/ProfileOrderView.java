package com.example.transferhall.models.views;

import com.example.transferhall.models.enums.CurrencyEnum;
import com.example.transferhall.models.enums.OrderStatusEnum;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProfileOrderView {
    private Long id;
    private String orderDescription;
    private String orderName;
    private LocalDateTime created;
    private BigDecimal totalPrice;
    private CurrencyEnum currency;
    private OrderStatusEnum orderStatus;

    public String getOrderDescription() {
        return orderDescription;
    }

    public ProfileOrderView setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public ProfileOrderView setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public ProfileOrderView setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public ProfileOrderView setCurrency(CurrencyEnum currency) {
        this.currency = currency;
        return this;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public ProfileOrderView setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ProfileOrderView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getOrderName() {
        return orderName;
    }

    public ProfileOrderView setOrderName(String orderName) {
        this.orderName = orderName;
        return this;
    }
}
