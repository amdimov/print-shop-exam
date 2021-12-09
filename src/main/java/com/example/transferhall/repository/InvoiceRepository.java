package com.example.transferhall.repository;

import com.example.transferhall.models.InvoicesEntity;
import com.example.transferhall.models.OrdersEntity;
import com.example.transferhall.models.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoicesEntity, Long> {
    Optional<InvoicesEntity> findByInvoiceNumber(String invoiceNumber);
    Optional<InvoicesEntity> findByPayedIsFalse();
    Optional<InvoicesEntity> findInvoicesEntityByInvoiceNumber(String invoiceNumber);
    Optional<InvoicesEntity> findInvoiceByOrdersId(Long orderId);

}
