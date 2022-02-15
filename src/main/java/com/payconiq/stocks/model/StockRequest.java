package com.payconiq.stocks.model;

import lombok.Data;

@Data
public class StockRequest {

    private String name;
    private double currentPrice;
}
