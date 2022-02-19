package com.payconiq.stocks.service;

import com.payconiq.stocks.exceptions.DataUpdateException;
import com.payconiq.stocks.model.StockRequest;
import com.payconiq.stocks.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class StockServiceImplTest {

    @InjectMocks
    private StockService stockService = new StockServiceImpl();

    @Mock
    private StockRepository stockRepository;

    /**
     * To check update call on service layer, if it throws exception or not when result status is not 1.
     */
    @Test
    void testWhenUpdateFails(){
        when(stockRepository.updateStockPrice(any(Double.class), any(Long.class))).thenReturn(0);
        DataUpdateException thrown = assertThrows(DataUpdateException.class, () -> {
            stockService.updateStock(new StockRequest(), 10);
        });
        assertEquals("Failed to update Stock price. Probably ID not found or DB connection problems.", thrown.getMessage());
    }
}
