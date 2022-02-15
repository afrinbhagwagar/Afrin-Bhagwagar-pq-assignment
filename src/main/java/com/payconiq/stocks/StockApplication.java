package com.payconiq.stocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * The starter class.
 */
@SpringBootApplication
public class StockApplication {

    /**
     * Method to start execution.
     *
     * @param args input arguments if any.
     */
    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
    }
}
