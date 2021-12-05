package com.example.transferhall.web.controllers;

import com.example.transferhall.models.serviceModels.OrdersApiDTO;
import com.example.transferhall.service.Impl.ApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrdersApiDTO>> fetchAllOrders(){
        List<OrdersApiDTO> orders = apiService.getAllBooks();
        return ResponseEntity.ok(orders);
    }
}
