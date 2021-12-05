package com.example.transferhall.models.bindingModels.admin.binding;

import com.example.transferhall.models.dto.OrderDetailsDTO;

import java.util.List;

public class OrderDetailsWrapper {
    private List<OrderDetailsDTO> orders;

    public List<OrderDetailsDTO> getOrders() {
        return orders;
    }

    public OrderDetailsWrapper setOrders(List<OrderDetailsDTO> orders) {
        this.orders = orders;
        return this;
    }
}
