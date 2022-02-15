package com.payconiq.stocks.repository;

import com.payconiq.stocks.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Modifying
    @Transactional
    @Query("update Stock s set s.currentPrice = :currentPrice where s.id = :stockId")
    Integer updateStockPrice(@Param(value = "currentPrice") double currentPrice, @Param(value = "stockId") long stockId);
}
