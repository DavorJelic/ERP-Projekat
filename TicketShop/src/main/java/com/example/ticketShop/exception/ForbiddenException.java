package com.example.ticketShop.exception;

public class ForbiddenException extends RuntimeException {

    private final String errorCode;

    public ForbiddenException(final String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
