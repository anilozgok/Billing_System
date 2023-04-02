package com.anilcan.billing_system.exception;

import com.anilcan.billing_system.exception.base.BaseException;
import com.anilcan.billing_system.exception.message.ExceptionMessage;
import com.fasterxml.jackson.databind.ser.Serializers;

public class InvalidBillNoException extends BaseException {

    public InvalidBillNoException() {
        super(ExceptionMessage.INVALID_BILL_NO_EXCEPTION);
    }

}
