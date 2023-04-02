package com.anilcan.billing_system.model.dto;

import com.anilcan.billing_system.enums.BillStatus;
import com.anilcan.billing_system.model.domain.BillDomain;

public record BillDTO(BillDomain info, BillStatus billStatus) {

}
