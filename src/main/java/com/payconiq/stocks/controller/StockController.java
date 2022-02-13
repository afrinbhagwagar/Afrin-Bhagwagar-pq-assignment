package com.payconiq.stocks.controller;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
            @RequestBody @NotEmpty(message = "Stock instance not available") StockRequest stockRequest) {
        StockResponse savedStock = stockService.saveStock(stockRequest);
        return ResponseEntity.ok(savedStock);
    }

    @GetMapping
    public List<StockResponse> getAllStocks() {
        return stockService.getAllStocks();
    }

}