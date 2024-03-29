package com.anilcan.billing_system.exception;

import com.anilcan.billing_system.exception.base.BaseException;
import com.anilcan.billing_system.exception.message.ExceptionMessage;

public class BillNotFoundException extends BaseException {

    public BillNotFoundException() {
        super(ExceptionMessage.BILL_NOT_FOUND_EXCEPTION);
    }

}
