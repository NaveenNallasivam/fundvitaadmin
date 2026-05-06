// language: java
package com.example.fundvitaadmin.dto;

import java.math.BigDecimal;

public class StockDto {

    // Stock (from StockImpl)
    private Long stockUidpk;
    private String code;
    private String description;
    private java.math.BigDecimal currentPrice;

    // Stock DE (from StockDeImpl)
    private Long stockDeUidpk;
    private BigDecimal stockDeValue;

    // Stock Markcap (from StockMarkcapImpl)
    private Long stockMarkcapUidpk;
    private BigDecimal stockMarkcapValue;

    // Stock PB (from StockPbImpl)
    private Long stockPbUidpk;
    private BigDecimal stockPbValue;

    // Stock PE (from StockPeImpl)
    private Long stockPeUidpk;
    private BigDecimal stockPeValue;

    public StockDto() {
    }

    // StockImpl fields
    public Long getStockUidpk() {
        return stockUidpk;
    }

    public void setStockUidpk(Long stockUidpk) {
        this.stockUidpk = stockUidpk;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.math.BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(java.math.BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    // StockDeImpl fields
    public Long getStockDeUidpk() {
        return stockDeUidpk;
    }

    public void setStockDeUidpk(Long stockDeUidpk) {
        this.stockDeUidpk = stockDeUidpk;
    }

    public BigDecimal getStockDeValue() {
        return stockDeValue;
    }

    public void setStockDeValue(BigDecimal stockDeValue) {
        this.stockDeValue = stockDeValue;
    }

    // StockMarkcapImpl fields
    public Long getStockMarkcapUidpk() {
        return stockMarkcapUidpk;
    }

    public void setStockMarkcapUidpk(Long stockMarkcapUidpk) {
        this.stockMarkcapUidpk = stockMarkcapUidpk;
    }

    public BigDecimal getStockMarkcapValue() {
        return stockMarkcapValue;
    }

    public void setStockMarkcapValue(BigDecimal stockMarkcapValue) {
        this.stockMarkcapValue = stockMarkcapValue;
    }

    // StockPbImpl fields
    public Long getStockPbUidpk() {
        return stockPbUidpk;
    }

    public void setStockPbUidpk(Long stockPbUidpk) {
        this.stockPbUidpk = stockPbUidpk;
    }

    public BigDecimal getStockPbValue() {
        return stockPbValue;
    }

    public void setStockPbValue(BigDecimal stockPbValue) {
        this.stockPbValue = stockPbValue;
    }

    // StockPeImpl fields
    public Long getStockPeUidpk() {
        return stockPeUidpk;
    }

    public void setStockPeUidpk(Long stockPeUidpk) {
        this.stockPeUidpk = stockPeUidpk;
    }

    public BigDecimal getStockPeValue() {
        return stockPeValue;
    }

    public void setStockPeValue(BigDecimal stockPeValue) {
        this.stockPeValue = stockPeValue;
    }
}
