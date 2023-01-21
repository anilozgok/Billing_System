package com.anilcan.billing_system.exception;


public class BillRejectedException extends RuntimeException {

    public BillRejectedException(Long billNo) {
        super("Bill with billNo: " + billNo + " is rejected. Bill is not valid.");
    }
}
