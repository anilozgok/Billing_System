package com.anilcan.billing_system.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillAcceptedResponse <T> {
    String Message;
    T bill;
}
