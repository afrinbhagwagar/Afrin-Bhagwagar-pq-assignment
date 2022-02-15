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

    /**
     * Get all stocks based on pagination inputs.
     *
     * @param pageNo which page to show for the output.
     * @param pageSize size of models/entity.
     * @param sortBy to be sort by which field.
     * @return list of all stocks as per the input in DB.
     */
    List<StockResponse> getAllStocks(Integer pageNo, Integer pageSize, String sortBy);

    /**
     * Get Stock response by ID.
     *
     * @param stockId and input iD for which stock is needed.
     * @return Single stock by ID.
     */
    StockResponse getStockById(long stockId);

    /**
     * Update stock by ID and the request body parameters such as currentPrice.
     *
     * @param stockRequest request body having parameters to be updated.
     * @param stockId ID for which these parameters need to be updated.
     * @return updated Stock.
     */
    StockResponse updateStock(StockRequest stockRequest, long stockId);

    /**
     * Delete Stock by input identifier.
     * @param stockId ID for which stock has to be deleted.
     */
    void deleteStockById(long stockId);
}
