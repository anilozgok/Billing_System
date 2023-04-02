package com.anilcan.billing_system.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    LIMIT_EXCEEDED_EXCEPTION("Total limit exceeded.", "limit.exceeded.error", HttpStatus.BAD_REQUEST),

    INVALID_BILL_NO_EXCEPTION("Invalid Bill Number", "invalid.bill.no", HttpStatus.BAD_REQUEST),

    BILL_NOT_FOUND_EXCEPTION("Bill Not Found", "bill.not.found", HttpStatus.NOT_FOUND),

    UNKNOWN_EXCEPTION("Unknown Error Occurred", "unknown.exception", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;

    private final String errorName;

    private final HttpStatus errorCode;
}
