package com.example.fundvitaadmin.entity.impl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "FUND_STOCK_DE")
public class StockDeImpl implements com.example.fundvitaadmin.entity.StockDe, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uidpk")
    private Long uidpk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_uid", nullable = false)
    private StockImpl stock;

    @Column(name = "value", precision = 19, scale = 4)
    private BigDecimal value;

    public StockDeImpl() {
    }

    public StockDeImpl(Long uidpk, StockImpl stock, BigDecimal value) {
        this.uidpk = uidpk;
        this.stock = stock;
        this.value = value;
    }

    @Override
    public Long getUidpk() {
        return uidpk;
    }

    @Override
    public void setUidpk(Long uidpk) {
        this.uidpk = uidpk;
    }

    @Override
    public Long getStockuid() {
        return stock != null ? stock.getUidpk() : null;
    }

    @Override
    public void setStockuid(Long stockuid) {
        if (this.stock == null) {
            this.stock = new StockImpl();
        }
        this.stock.setUidpk(stockuid);
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public StockImpl getStock() {
        return stock;
    }

    public void setStock(StockImpl stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockDeImpl)) return false;
        StockDeImpl that = (StockDeImpl) o;
        if (uidpk != null && that.uidpk != null) {
            return Objects.equals(uidpk, that.uidpk);
        }
        return Objects.equals(getStockuid(), that.getStockuid()) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        if (uidpk != null) {
            return Objects.hash(uidpk);
        }
        return Objects.hash(getStockuid(), value);
    }

    @Override
    public String toString() {
        return "StockDeImpl{" +
                "uidpk=" + uidpk +
                ", stockuid=" + getStockuid() +
                ", value=" + value +
                '}';
    }
}
