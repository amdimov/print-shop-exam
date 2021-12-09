package com.example.transferhall.service.Impl;

import com.example.transferhall.models.InvoicesEntity;
import com.example.transferhall.models.dto.InvoiceDTO;
import com.example.transferhall.repository.InvoiceRepository;
import com.example.transferhall.service.InvoiceService;
import com.example.transferhall.util.exceptions.InvoiceNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ModelMapper modelMapper;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, ModelMapper modelMapper) {
        this.invoiceRepository = invoiceRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public InvoiceDTO findInvoiceByInvoiceNumber(String invoiceNumber) {
        InvoicesEntity invoice = invoiceRepository.
                findInvoicesEntityByInvoiceNumber(invoiceNumber).
                orElseThrow(()->new InvoiceNotFound("Invoice with this number doesn't exist"));
        return modelMapper.map(invoice, InvoiceDTO.class);
    }

    @Transactional
    @Override
    public Optional<InvoiceDTO> findInvoiceByOrderId(Long orderId) {
        Optional<InvoicesEntity> invoiceEntity = invoiceRepository.findInvoiceByOrdersId(orderId);
        return Optional.of(
                invoiceEntity.map(invoice -> modelMapper.map(invoice, InvoiceDTO.class))
                .orElseThrow(()-> new InvoiceNotFound("No invoice for this order ID"))
        );
    }
}
