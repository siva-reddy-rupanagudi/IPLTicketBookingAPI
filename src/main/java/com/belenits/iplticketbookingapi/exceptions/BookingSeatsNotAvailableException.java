package com.belenits.iplticketbookingapi.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;


public class BookingSeatsNotAvailableException extends RuntimeException{


    public BookingSeatsNotAvailableException(String msg) {
        super(msg);
    }
}
