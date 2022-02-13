package com.payconiq.stocks.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Stock DTO class with getters / setters.
 */
@Data
public class StockResponse {

    private long id;
    private String name;
    private double currentPrice;
    private LocalDateTime lastUpdate;

    public StockResponse(long id, String name, double currentPrice, LocalDateTime lastUpdate) {
        super();
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.lastUpdate = lastUpdate;
    }

}