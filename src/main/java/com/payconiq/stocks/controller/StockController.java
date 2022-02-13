package com.payconiq.stocks.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.payconiq.stocks.exceptions.StockNotFoundException;
import com.payconiq.stocks.model.StockRequest;
import com.payconiq.stocks.model.StockResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.payconiq.stocks.service.StockService;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    /**
     * post controller to save stocks.
     *
     * @param stockRequest a particular stock to be saved in database.
     * @return ResponseEntity stock output which is saved in DB along with its ID, timestamp
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<StockResponse> saveStock(
            @RequestBody StockRequest stockRequest) {
        StockResponse savedStock = stockService.saveStock(stockRequest);
        return ResponseEntity.ok(savedStock);
    }

    /**
     * get controller to get all stocks.
     *
     * @return List list of all Stocks present in database.
     */
    @GetMapping
    public List<StockResponse> getAllStocks() {
        return stockService.getAllStocks();
    }

    /**
     * get controller to get stock based on ID.
     *
     * @param stockId id representing which stock should be fetched.
     * @return StockDto a particular stock based on the stockId present.
     */
    @GetMapping("/{stockId}")
    public StockResponse getStockById(@PathVariable long stockId) {
        StockResponse stockResponse = null;
        try {
            stockResponse = stockService.getStockById(stockId);
        } catch (EntityNotFoundException enfe) {
            throw new StockNotFoundException(enfe.getMessage());
        }
        return stockResponse;
    }

    @PatchMapping("/{stockId}")
    public ResponseEntity<StockResponse> updateStock(
            @RequestBody StockRequest stockRequest, @PathVariable long stockId) {
        return ResponseEntity.ok(stockService.updateStock(stockRequest, stockId));
    }

}