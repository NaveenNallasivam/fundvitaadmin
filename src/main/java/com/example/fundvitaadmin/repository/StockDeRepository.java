package com.example.fundvitaadmin.repository;

import com.example.fundvitaadmin.entity.impl.StockDeImpl;
import com.example.fundvitaadmin.entity.impl.StockImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface StockDeRepository extends JpaRepository<StockDeImpl, Long> {

    @Modifying
    @Transactional
    @Query("delete from StockDeImpl d where d.stock = :stock")
    void deleteByStock(StockImpl stock);
}

