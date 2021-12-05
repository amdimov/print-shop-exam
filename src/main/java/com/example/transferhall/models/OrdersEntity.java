package com.example.transferhall.models;

import com.example.transferhall.models.enums.CurrencyEnum;
import com.example.transferhall.models.enums.OrderStatusEnum;
import com.example.transferhall.models.enums.TransferCategoryEnum;
import com.example.transferhall.models.enums.UnitTypeEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrdersEntity extends BaseEntity{
    @Column
    private String orderName;

    @Column(name = "order_code", nullable = false, unique = true)
    private String orderCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatusEnum orderStatus = OrderStatusEnum.PENDING;

    @Column(name = "order_description", nullable = false)
    @Lob
    private String orderDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit_type_enum", nullable = false)
    private UnitTypeEnum unitTypeEnum;

    @Column(nullable = false)
    private Integer quantity;

    @Column
    private Integer numOfColors;

    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;

    @Column
    private LocalDateTime created;

    @Column(name = "price_per_unit")
    private BigDecimal pricePerUnit;

    @Column
    private BigDecimal shippingCost;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "file_url")
    private String fileUrl;

    @Column
    private String publicId;

    @Column(name = "confirmed_order")
    private Boolean confirmedOrder = false;

    @Column(name = "discount_percentage")
    private Integer discountPercentage = 0;

    @ManyToOne
    private UsersEntity users;

    @Column
    private String printSize;

    @ManyToOne
    private InvoicesEntity invoices;

    @Enumerated(EnumType.STRING)
    @Column(name = "transfer_category")
    private TransferCategoryEnum transferCategory;

    @Column(name = "apparel_color")
    private String apparelColor;

    @Column
    private LocalDate deadline;

    @Column
    private LocalDate deletionDate;

    @Column
    @Lob
    private String messageFromAdmin;

    public OrdersEntity() {
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public OrdersEntity setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
        return this;
    }

    public UnitTypeEnum getUnitTypeEnum() {
        return unitTypeEnum;
    }

    public OrdersEntity setUnitTypeEnum(UnitTypeEnum unitTypeEnum) {
        this.unitTypeEnum = unitTypeEnum;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrdersEntity setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public OrdersEntity setCurrency(CurrencyEnum currency) {
        this.currency = currency;
        return this;
    }


    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public OrdersEntity setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrdersEntity setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }


    public String getFileUrl() {
        return fileUrl;
    }

    public OrdersEntity setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
        return this;
    }


    public LocalDateTime getCreated() {
        return created;
    }

    public OrdersEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }


    public Boolean getConfirmedOrder() {
        return confirmedOrder;
    }

    public OrdersEntity setConfirmedOrder(Boolean confirmedOrder) {
        this.confirmedOrder = confirmedOrder;
        return this;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public OrdersEntity setOrderCode(String orderNumber) {
        this.orderCode = orderNumber;
        return this;
    }

    public UsersEntity getUsers() {
        return users;
    }

    public OrdersEntity setUsers(UsersEntity users) {
        this.users = users;
        return this;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public OrdersEntity setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
        return this;
    }

    public InvoicesEntity getInvoices() {
        return invoices;
    }

    public OrdersEntity setInvoices(InvoicesEntity invoices) {
        this.invoices = invoices;
        return this;
    }

    public String getOrderName() {
        return orderName;
    }

    public OrdersEntity setOrderName(String name) {
        this.orderName = name;
        return this;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public OrdersEntity setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public TransferCategoryEnum getTransferCategory() {
        return transferCategory;
    }

    public OrdersEntity setTransferCategory(TransferCategoryEnum transferCategory) {
        this.transferCategory = transferCategory;
        return this;
    }

    public String getApparelColor() {
        return apparelColor;
    }

    public OrdersEntity setApparelColor(String apparelColor) {
        this.apparelColor = apparelColor;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public OrdersEntity setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public OrdersEntity setDeadline(LocalDate deadline) {
        this.deadline = deadline;
        return this;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public OrdersEntity setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
        return this;
    }

    public LocalDate getDeletionDate() {
        return deletionDate;
    }

    public OrdersEntity setDeletionDate(LocalDate deletionDate) {
        this.deletionDate = deletionDate;
        return this;
    }

    public String getMessageFromAdmin() {
        return messageFromAdmin;
    }

    public OrdersEntity setMessageFromAdmin(String messageFromAdmin) {
        this.messageFromAdmin = messageFromAdmin;
        return this;
    }

    public String getPrintSize() {
        return printSize;
    }

    public OrdersEntity setPrintSize(String printSize) {
        this.printSize = printSize;
        return this;
    }

    public Integer getNumOfColors() {
        return numOfColors;
    }

    public OrdersEntity setNumOfColors(Integer numOfColors) {
        this.numOfColors = numOfColors;
        return this;
    }
}
