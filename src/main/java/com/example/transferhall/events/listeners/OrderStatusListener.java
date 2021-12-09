package com.example.transferhall.events.listeners;

import com.example.transferhall.events.ChangeOrderStatusEvent;
import com.example.transferhall.models.InvoicesEntity;
import com.example.transferhall.models.enums.OrderStatusEnum;
import com.example.transferhall.repository.InvoiceRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class OrderStatusListener {
    private final InvoiceRepository invoiceRepository;

    public OrderStatusListener(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Transactional
    @EventListener(ChangeOrderStatusEvent.class)
    public void onInvoiceCreated(ChangeOrderStatusEvent statusEvent){
        Optional<InvoicesEntity> invoice = invoiceRepository
                .findInvoicesEntityByInvoiceNumber(statusEvent.getInvoiceNumber());
        invoice.ifPresent(invoicesEntity -> invoicesEntity.getOrders()
                .forEach(order -> order.setOrderStatus(OrderStatusEnum.QUOTED)));
        invoiceRepository.save(invoice.get());
    }
}
