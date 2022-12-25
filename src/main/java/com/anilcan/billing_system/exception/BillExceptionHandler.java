package com.anilcan.billing_system.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BillExceptionHandler {

    @ExceptionHandler(BillRejectedException.class)
    @ResponseBody
    public BillException handeBillRejectedResponse(BillRejectedException e) {
        BillException billException = new BillException();
        billException.setErrorMessage(e.getMessage());
        return billException;
    }

}
