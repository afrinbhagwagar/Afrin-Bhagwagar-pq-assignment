package com.payconiq.stocks.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class StockRequest {

    private String name;
    private double currentPrice;
}
