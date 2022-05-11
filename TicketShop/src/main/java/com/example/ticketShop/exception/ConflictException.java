package com.example.ticketShop.exception;

public class ConflictException extends RuntimeException {

    private final String errorCode;

    public ConflictException(final String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
