package com.anilcan.billing_system.exception.base;

import com.anilcan.billing_system.exception.message.ExceptionMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseException extends RuntimeException {

    protected final ExceptionMessage exceptionMessage;

}
