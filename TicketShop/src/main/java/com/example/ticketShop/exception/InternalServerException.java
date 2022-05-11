package com.example.ticketShop.exception;

public class InternalServerException extends RuntimeException {

    private final String errorCode;

    public InternalServerException(final String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
