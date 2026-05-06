package com.example.fundvitaadmin.repository;

import com.example.fundvitaadmin.entity.impl.StockPbImpl;
import com.example.fundvitaadmin.entity.impl.StockImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface StockPbRepository extends JpaRepository<StockPbImpl, Long> {

    @Modifying
    @Transactional
    @Query("delete from StockPbImpl p where p.stock = :stock")
    void deleteByStock(StockImpl stock);
}

