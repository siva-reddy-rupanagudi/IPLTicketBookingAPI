package com.belenits.iplticketbookingapi.exceptions;

public class BookingIdNotFoundException extends RuntimeException{
    public BookingIdNotFoundException(String msg) {
        super(msg);
    }
}
