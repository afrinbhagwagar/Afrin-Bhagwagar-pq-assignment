package com.payconiq.stocks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class DataUpdateException extends RuntimeException{

    public DataUpdateException(String excMessage) {
        super(excMessage);
    }
}
