package com.anilcan.billing_system.repository;

import com.anilcan.billing_system.model.entity.Bill;
import com.anilcan.billing_system.model.response.BillResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository  extends JpaRepository<Bill, Long> {

    BillResponse getAcceptedBills();

    BillResponse getRejectedBills();
}
