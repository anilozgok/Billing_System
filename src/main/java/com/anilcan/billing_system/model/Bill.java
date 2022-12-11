package com.anilcan.billing_system.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public record Bill(
        Long billNo,
        String firstName,
        String lastName,
        String email,
        double amount,
        String productName) { }

//NOTE: records are immutable therefore it doesn't have setters