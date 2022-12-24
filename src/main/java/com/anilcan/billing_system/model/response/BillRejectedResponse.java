package com.anilcan.billing_system.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillRejectedResponse <T> {
    String Message;
    T bill;
}
