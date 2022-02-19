package com.payconiq.stocks.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.payconiq.stocks.exceptions.StockNotFoundException;
import com.payconiq.stocks.model.StockRequest;
import com.payconiq.stocks.model.StockResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.payconiq.stocks.service.StockService;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    private Logger logger = LoggerFactory.getLogger(StockController.class);

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
    public List<StockResponse> getAllStocks(@RequestParam(defaultValue = "0") Integer pageNo,
                                            @RequestParam(defaultValue = "5") Integer pageSize,
                                            @RequestParam(defaultValue = "id") String sortBy) {
        return stockService.getAllStocks(pageNo, pageSize, sortBy);
    }

    /**
     * get controller to get stock based on ID.
     *
     * @param stockId id representing which stock should be fetched.
     * @return StockDto a particular stock based on the stockId present.
     */
    @GetMapping("/{stockId}")
    public StockResponse getStockById(@PathVariable long stockId) {
        return stockService.getStockById(stockId);
    }

    @PatchMapping("/{stockId}")
    public ResponseEntity<StockResponse> updateStock(
            @RequestBody StockRequest stockRequest, @PathVariable long stockId) {
        return ResponseEntity.ok(stockService.updateStock(stockRequest, stockId));
    }

    /**
     * delete controller to delete a particular stock.
     *
     * @param stockId delete by stockId.
     */
    @DeleteMapping("/{stockId}")
    public ResponseEntity<Void> deleteStockById(@PathVariable long stockId) {
        try {
           stockService.deleteStockById(stockId);
        } catch (EmptyResultDataAccessException erdae) {
            logger.error("Stock ID not present to delete. Throwing StockNotFoundException.");
            throw new StockNotFoundException(erdae.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

}