package com.anilcan.billing_system.exception;

import com.anilcan.billing_system.exception.base.BaseException;
import com.anilcan.billing_system.exception.message.ExceptionMessage;

public class LimitExceededException extends BaseException {

    public LimitExceededException() {
        super(ExceptionMessage.LIMIT_EXCEEDED_EXCEPTION);
    }

}
