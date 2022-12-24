package com.anilcan.billing_system.model.entity;

import lombok.Builder;

@Builder
public record Bill(
        Long billNo,
        String firstName,
        String lastName,
        String email,
        double amount,
        String productName,
        boolean billStatus) { }

//NOTE: records are immutable therefore it doesn't have setters