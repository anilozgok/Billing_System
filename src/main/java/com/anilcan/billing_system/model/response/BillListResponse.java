package com.anilcan.billing_system.model.response;

import com.anilcan.billing_system.model.entity.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BillListResponse {
    private List<Bill> bills;
}
