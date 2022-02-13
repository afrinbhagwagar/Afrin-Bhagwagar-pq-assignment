package com.payconiq.stocks.service;

import com.payconiq.stocks.model.StockRequest;
import com.payconiq.stocks.model.StockResponse;

import java.util.List;

public interface StockService {

    /**
     * Save stocks.
     *
     * @param stock a particular stock to be saved in database.
     * @return Stock what has been saved.
     */
    StockResponse saveStock(StockRequest stock);

    List<StockResponse> getAllStocks();

    StockResponse getStockById(long stockId);

    StockResponse updateStock(StockRequest stockRequest, long stockId);
}
