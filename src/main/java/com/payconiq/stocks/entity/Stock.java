package com.payconiq.stocks.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Entity class for stock.
 */
@Getter
@Setter
@Entity
public class Stock {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "NAME")
    @Size(min = 3, max = 30, message = "Size should be between 3 and 30")
    @NotEmpty(message = "Enter some valid stock name")
    private String name;

    @Column(nullable = false, name = "CURRENT_PRICE")
    @Min(0)
    private double currentPrice;

    @Column(nullable = false, name = "LAST_UPDATE")
    private LocalDateTime lastUpdate;

    public Stock() {}

    public Stock(String name, double currentPrice, LocalDateTime lastUpdate) {
        super();
        this.name = name;
        this.currentPrice = currentPrice;
        this.lastUpdate = lastUpdate;
    }

}