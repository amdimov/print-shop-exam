package com.example.transferhall.models.dto;
import com.example.transferhall.models.UsersEntity;
import com.example.transferhall.models.enums.CurrencyEnum;
import com.example.transferhall.models.enums.OrderStatusEnum;
import com.example.transferhall.models.enums.TransferCategoryEnum;
import com.example.transferhall.models.enums.UnitTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderDetailsDTO {

    private Long id;
    private String orderName;
    private String orderCode;
    private OrderStatusEnum orderStatus;
    private String orderDescription;
    private UnitTypeEnum unitTypeEnum;
    private Integer quantity;
    private CurrencyEnum currency;
    private LocalDateTime created;
    private BigDecimal pricePerUnit;
    private BigDecimal totalPrice;
    private BigDecimal shippingCost;
    private String printSize;
    private Integer numOfColors;
    private String fileUrl;
    private String publicId;
    private Boolean confirmedOrder;
    private Integer discountPercentage;
    private TransferCategoryEnum transferCategory;
    private String apparelColor;
    private LocalDate deadline;
    private String messageFromAdmin;

    public String getOrderName() {
        return orderName;
    }

    public OrderDetailsDTO setOrderName(String orderName) {
        this.orderName = orderName;
        return this;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public OrderDetailsDTO setOrderCode(String orderCode) {
        this.orderCode = orderCode;
        return this;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public OrderDetailsDTO setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public OrderDetailsDTO setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
        return this;
    }

    public UnitTypeEnum getUnitTypeEnum() {
        return unitTypeEnum;
    }

    public OrderDetailsDTO setUnitTypeEnum(UnitTypeEnum unitTypeEnum) {
        this.unitTypeEnum = unitTypeEnum;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderDetailsDTO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public OrderDetailsDTO setCurrency(CurrencyEnum currency) {
        this.currency = currency;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public OrderDetailsDTO setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public OrderDetailsDTO setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderDetailsDTO setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public OrderDetailsDTO setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public OrderDetailsDTO setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public Boolean getConfirmedOrder() {
        return confirmedOrder;
    }

    public OrderDetailsDTO setConfirmedOrder(Boolean confirmedOrder) {
        this.confirmedOrder = confirmedOrder;
        return this;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public OrderDetailsDTO setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
        return this;
    }

    public TransferCategoryEnum getTransferCategory() {
        return transferCategory;
    }

    public OrderDetailsDTO setTransferCategory(TransferCategoryEnum transferCategory) {
        this.transferCategory = transferCategory;
        return this;
    }

    public String getApparelColor() {
        return apparelColor;
    }

    public OrderDetailsDTO setApparelColor(String apparelColor) {
        this.apparelColor = apparelColor;
        return this;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public OrderDetailsDTO setDeadline(LocalDate deadline) {
        this.deadline = deadline;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OrderDetailsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public OrderDetailsDTO setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
        return this;
    }

    public String getPrintSize() {
        return printSize;
    }

    public OrderDetailsDTO setPrintSize(String printSize) {
        this.printSize = printSize;
        return this;
    }

    public Integer getNumOfColors() {
        return numOfColors;
    }

    public OrderDetailsDTO setNumOfColors(Integer numOfColors) {
        this.numOfColors = numOfColors;
        return this;
    }

    public String getMessageFromAdmin() {
        return messageFromAdmin;
    }

    public OrderDetailsDTO setMessageFromAdmin(String messageFromAdmin) {
        this.messageFromAdmin = messageFromAdmin;
        return this;
    }

}
