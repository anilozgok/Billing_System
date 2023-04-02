package com.anilcan.billing_system.model.domain;

import com.anilcan.billing_system.enums.BillStatus;

public record BillDomain(String billNo, String firstName, String lastName, String email, double amount, String productName) {

}
