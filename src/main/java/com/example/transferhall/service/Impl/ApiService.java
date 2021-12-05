package com.example.transferhall.service.Impl;

import com.example.transferhall.models.serviceModels.OrdersApiDTO;
import com.example.transferhall.repository.OrdersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiService {
    private OrdersRepository ordersRepository;
    private ModelMapper modelMapper;

    public ApiService(OrdersRepository ordersRepository, ModelMapper modelMapper) {
        this.ordersRepository = ordersRepository;
        this.modelMapper = modelMapper;
    }

    public List<OrdersApiDTO> getAllBooks(){
        
        return this.ordersRepository.findAll().stream()
                .map(e -> modelMapper.map(e, OrdersApiDTO.class))
                .collect(Collectors.toList());
    }
}
