package com.anilcan.billing_system.model.response;

import com.anilcan.billing_system.exception.message.ExceptionMessage;

import java.time.LocalDateTime;

public record ErrorResponse(ExceptionMessage message, LocalDateTime occuredAt) {

}
