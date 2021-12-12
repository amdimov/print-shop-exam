package com.example.transferhall.repository;

import com.example.transferhall.models.OrdersEntity;
import com.example.transferhall.models.UsersEntity;
import com.example.transferhall.models.enums.OrderStatusEnum;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {
    @Cacheable(value = "orders")
    List<OrdersEntity> findAllByOrderStatus(OrderStatusEnum orderStatus);
}
