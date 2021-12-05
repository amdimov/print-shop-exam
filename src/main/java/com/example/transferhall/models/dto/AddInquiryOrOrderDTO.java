package com.example.transferhall.models.dto;

import com.example.transferhall.models.enums.CurrencyEnum;
import com.example.transferhall.models.enums.OrderStatusEnum;
import com.example.transferhall.models.enums.UnitTypeEnum;
import com.example.transferhall.util.validator.FileIsNotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddInquiryOrOrderDTO {
    @NotBlank
    private String orderName;
    private String orderDescription;
    @Min(value = 1, message = "Please add quantity")
    @NotNull
    private Integer quantity;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;
    @FileIsNotEmpty(message = "File empty")
    private MultipartFile orderFile;
    private String transferCategory;
    private String apparelColor;

    public String getOrderName() {
        return orderName;
    }

    public AddInquiryOrOrderDTO setOrderName(String orderName) {
        this.orderName = orderName;
        return this;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public AddInquiryOrOrderDTO setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public AddInquiryOrOrderDTO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public AddInquiryOrOrderDTO setDeadline(LocalDate deadline) {
        this.deadline = deadline;
        return this;
    }

    public MultipartFile getOrderFile() {
        return orderFile;
    }

    public AddInquiryOrOrderDTO setOrderFile(MultipartFile orderFile) {
        this.orderFile = orderFile;
        return this;
    }

    public String getTransferCategory() {
        return transferCategory;
    }

    public AddInquiryOrOrderDTO setTransferCategory(String transferCategory) {
        this.transferCategory = transferCategory;
        return this;
    }

    public String getApparelColor() {
        return apparelColor;
    }

    public AddInquiryOrOrderDTO setApparelColor(String apparelColor) {
        this.apparelColor = apparelColor;
        return this;
    }
}
