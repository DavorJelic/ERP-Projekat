package com.example.ticketShop.constants;

public final class ErrorCodes {

    //user
    public static final String USER_ALREADY_EXISTS = "U001";
    public static final String USER_NOT_FOUND_BY_NAME = "U002";
    public static final String USER_NOT_FOUND_BY_ID_1 = "U003.1";
    public static final String USER_NOT_FOUND_BY_ID_2 = "U003.2";

    // general
    public static final String DTO_VALIDATION_FAILED = "G001";
    public static final String BAD_CREDENTIALS = "G002";
    public static final String UNAUTHORIZED = "G003";
    public static final String INSUFFICIENT_PRIVILEGES = "G004";
    public static final String ACQUIRING_LOCK_FAILED = "G005";
    public static final String OLD_OBJECT_VERSION = "G006";
    public static final String MISSING_REQUIRED_PARAMETERS = "G007";
    public static final String MEDIA_TYPE_NOT_SUPPORTED = "G008";

    // event
    public static final String EVENT_NOT_FOUND_1 = "R001.1";
    public static final String EVENT_NOT_FOUND_2 = "R001.2";
    public static final String EVENT_NOT_FOUND_3 = "R001.3";
    public static final String EVENT_NOT_FOUND_4 = "R001.4";
    public static final String EVENT_NOT_FOUND_5 = "R001.5";
    public static final String EVENT_NOT_FOUND_6 = "R001.6";
    public static final String EVENT_NOT_FOUND_7 = "R001.7";
    public static final String EVENT_ALREADY_EXISTS = "R002";
    public static final String EVENT_NOT_ASSIGNED_TO_USER = "R003";
    public static final String EVENT_ALREADY_ASSIGNED_TO_USER = "R004";

    // ticket
    public static final String TICKET_TYPE_ALREADY_EXISTS = "T001";
    public static final String TICKET_TYPE_NOT_FOUND = "T002";

    // invoice
    public static final String INVOICE_NOT_FOUND = "I001";

    // login
    public static final String LOGIN_ERROR = "L001";

    // token
    public static final String TOKEN_EXPIRED = "TO001";

    private ErrorCodes() {
        super();
    }
}
