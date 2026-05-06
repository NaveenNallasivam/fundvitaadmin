package com.example.fundvitaadmin.repository;

import com.example.fundvitaadmin.entity.impl.StockMarkcapImpl;
import com.example.fundvitaadmin.entity.impl.StockImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface StockMarkcapRepository extends JpaRepository<StockMarkcapImpl, Long> {

    @Modifying
    @Transactional
    @Query("delete from StockMarkcapImpl m where m.stock = :stock")
    void deleteByStock(StockImpl stock);
}

