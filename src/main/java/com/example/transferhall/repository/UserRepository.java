package com.example.transferhall.repository;

import com.example.transferhall.models.OrdersEntity;
import com.example.transferhall.models.UsersEntity;
import com.example.transferhall.models.enums.OrderStatusEnum;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    @Query("select u from UsersEntity u left join fetch u.orders where u.email = ?1")
    Optional<UsersEntity> findUsersLazyEntityByEmail(String email);
    Optional<UsersEntity> findUsersEntityByEmail(String email);

    @EntityGraph(value = "user-orders")
    @Query("select distinct u from UsersEntity u left join fetch u.orders o where o.orderStatus = ?1")
    List<UsersEntity> findAllUsersWithOrderStatus(OrderStatusEnum status);

}
