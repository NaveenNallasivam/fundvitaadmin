package com.example.fundvitaadmin.entity.impl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "FUND_STOCK")
public class StockImpl implements com.example.fundvitaadmin.entity.Stock, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uidpk")
    private Long uidpk;

    @Column(name = "code", nullable = false, length = 100)
    private String code;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "current_price", precision = 19, scale = 4)
    private BigDecimal currentPrice;

    public StockImpl() {
    }

    public StockImpl(Long uidpk, String code, String description) {
        this.uidpk = uidpk;
        this.code = code;
        this.description = description;
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
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        StockImpl stock = (StockImpl) o;
        if (uidpk != null && stock.uidpk != null) {
            return Objects.equals(uidpk, stock.uidpk);
        }
        return Objects.equals(code, stock.code);
    }

    @Override
    public int hashCode() {
        if (uidpk != null) {
            return Objects.hash(uidpk);
        }
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "StockImpl{" +
                "uidpk=" + uidpk +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
