package com.example.fundvitaadmin.repository;

import com.example.fundvitaadmin.entity.impl.StockImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<StockImpl, Long> {
    Optional<StockImpl> findByCode(String code);
}

