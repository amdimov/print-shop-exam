package com.example.transferhall.service;

import com.example.transferhall.models.dto.InvoiceDTO;

import java.util.Optional;

public interface InvoiceService {
    InvoiceDTO findInvoiceByInvoiceNumber(String invoiceNumber);
    Optional<InvoiceDTO> findInvoiceByOrderId(Long orderId);
}
