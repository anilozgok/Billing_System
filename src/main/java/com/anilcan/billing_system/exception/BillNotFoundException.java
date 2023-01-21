package com.anilcan.billing_system.exception;

public class BillNotFoundException extends RuntimeException {
    public BillNotFoundException(Long billNo) {
        super("Bill with billNo: " + billNo + " not found.");
    }

    public BillNotFoundException(String message) {
        super(message);
    }
}
