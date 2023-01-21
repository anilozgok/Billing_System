package com.anilcan.billing_system.model.response;

import com.anilcan.billing_system.model.entity.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class BillAcceptedResponse {
    private String Message;
    private Bill bill;

}
