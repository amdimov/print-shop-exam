package com.example.transferhall.events;

import org.springframework.context.ApplicationEvent;

public class ChangeOrderStatusEvent extends ApplicationEvent {
    private final String invoiceNumber;

    public ChangeOrderStatusEvent(Object source, String invoiceNumber) {
        super(source);
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }
}
