package com.anilcan.billing_system.repository;

import com.anilcan.billing_system.enums.BillStatus;
import com.anilcan.billing_system.model.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    Optional<Bill> findBillByBillNo(String billNo);

    List<Bill> findBillByBillStatus(BillStatus billStatus);

    List<Bill> findBillByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);

}
