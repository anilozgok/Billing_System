package com.anilcan.billing_system.exception;

import lombok.Data;

@Data
public class BillRejectedException extends RuntimeException {

    private final String message;

    public BillRejectedException(String message) {
        super(message);
        this.message = message;
    }
}
