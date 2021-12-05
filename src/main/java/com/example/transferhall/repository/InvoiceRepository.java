package com.example.transferhall.repository;

import com.example.transferhall.models.InvoicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoicesEntity, Long> {
}
