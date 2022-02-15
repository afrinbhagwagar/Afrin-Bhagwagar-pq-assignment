package com.payconiq.stocks.model;

import lombok.Data;

/**
 * Stock rquest model for different controllers.
 */
@Data
public class StockRequest {

    private String name;
    private double currentPrice;
}
