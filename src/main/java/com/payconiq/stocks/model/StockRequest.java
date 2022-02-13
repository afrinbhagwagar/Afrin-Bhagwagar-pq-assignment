package com.payconiq.stocks.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class StockRequest {

    @NotEmpty(message = "Stock name not present")
    private String name;

    @NotEmpty(message = "Stock name not present")
    private double currentPrice;
}
