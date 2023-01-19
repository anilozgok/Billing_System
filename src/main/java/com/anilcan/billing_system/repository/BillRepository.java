package com.anilcan.billing_system.repository;

import com.anilcan.billing_system.model.entity.Bill;
import com.anilcan.billing_system.model.response.BillResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository  extends JpaRepository<Bill, Long> {

    List<Bill> getAcceptedBills();

    List<Bill> getRejectedBills();
}
