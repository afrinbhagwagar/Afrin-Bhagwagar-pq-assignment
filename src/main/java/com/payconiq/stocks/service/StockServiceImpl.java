package com.payconiq.stocks.service;

import com.payconiq.stocks.entity.Stock;
import com.payconiq.stocks.exceptions.DataUpdateException;
import com.payconiq.stocks.exceptions.StockNotFoundException;
import com.payconiq.stocks.model.StockRequest;
import com.payconiq.stocks.model.StockResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.payconiq.stocks.repository.StockRepository;
import com.payconiq.stocks.utils.StockConverter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public StockResponse saveStock(StockRequest stockRequest) {
        return StockConverter.entityToDto(stockRepository.save(StockConverter.dtoToEntity(stockRequest)));
    }

    @Override
    public List<StockResponse> getAllStocks(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return stockRepository.findAll(paging).stream().map(StockConverter::entityToDto).collect(Collectors.toList());
    }

    @Override
    public StockResponse getStockById(long stockId) throws StockNotFoundException{
        Optional<Stock> opt = stockRepository.findById(stockId);
        if(opt.isPresent())
            return StockConverter.entityToDto(stockRepository.findById(stockId).get());
        else
            throw new StockNotFoundException("No stock found by the input ID.");
    }

    @Override
    public StockResponse updateStock(StockRequest stockRequest, long stockId) {
        int priceUpdateOrNot = stockRepository.updateStockPrice(stockRequest.getCurrentPrice(), stockId);
        if(priceUpdateOrNot==1)
            return StockConverter.entityToDto(stockRepository.findById(stockId).get());
        throw new DataUpdateException("Failed to update Stock price. Probably ID not found or DB connection problems.");
    }

    @Override
    public void deleteStockById(long stockId) {
        stockRepository.deleteById(stockId);
    }
}