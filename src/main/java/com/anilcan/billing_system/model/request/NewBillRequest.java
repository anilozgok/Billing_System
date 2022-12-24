package com.anilcan.billing_system.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewBillRequest {
    private Long billNo;
    private String firstName;
    private String lastName;
    private String email;
    private double amount;
    private String productName;
}
