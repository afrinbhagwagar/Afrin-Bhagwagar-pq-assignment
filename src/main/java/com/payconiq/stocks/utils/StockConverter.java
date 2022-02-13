package com.payconiq.stocks.utils;

import java.time.LocalDateTime;

import com.payconiq.stocks.entity.Stock;
import com.payconiq.stocks.model.StockRequest;
import com.payconiq.stocks.model.StockResponse;

/**
 * Utility class to manage model/entity.
 */
public class StockConverter {

    /**
     * Method responsible for DTO to Entity conversion.
     *
     * @param stockRequest stockRequest as an input.
     * @return Stock as return value.
     */
    public static Stock dtoToEntity(StockRequest stockRequest) {
        return new Stock(stockRequest.getName(), stockRequest.getCurrentPrice(), LocalDateTime.now());
    }

    /**
     * Method responsible for Entity to DTO conversion.
     *
     * @param stock as an input.
     * @return StockResponse returned DTO.
     */
    public static StockResponse entityToDto(Stock stock) {
        return new StockResponse(stock.getId(), stock.getName(), stock.getCurrentPrice(), stock.getLastUpdate());
    }

}