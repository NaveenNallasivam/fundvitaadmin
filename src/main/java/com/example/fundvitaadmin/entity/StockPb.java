package com.example.fundvitaadmin.entity;

import java.math.BigDecimal;

public interface StockPb {
    Long getUidpk();
    void setUidpk(Long uidpk);

    Long getStockuid();
    void setStockuid(Long stockuid);

    BigDecimal getValue();
    void setValue(BigDecimal value);
}

