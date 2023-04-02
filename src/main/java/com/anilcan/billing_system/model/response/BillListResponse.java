package com.anilcan.billing_system.model.response;

import com.anilcan.billing_system.model.dto.BillDTO;

import java.util.List;

public record BillListResponse(List<BillDTO> bills) {


}
