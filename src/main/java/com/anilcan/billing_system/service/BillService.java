package com.anilcan.billing_system.service;

import com.anilcan.billing_system.enums.BillStatus;
import com.anilcan.billing_system.exception.BillNotFoundException;
import com.anilcan.billing_system.exception.InvalidBillNoException;
import com.anilcan.billing_system.exception.LimitExceededException;
import com.anilcan.billing_system.model.domain.BillDomain;
import com.anilcan.billing_system.model.dto.BillDTO;
import com.anilcan.billing_system.model.entity.Bill;
import com.anilcan.billing_system.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository billRepository;

    @Value("${billing-system.limit}")
    private double limit;

    public BillDTO saveBill(BillDomain bill) {
        log.info("BS - processing NewBillRequest.");

        var billNoUpperCase = bill.billNo().toUpperCase();

        if (!billNoUpperCase.startsWith("TR")) throw new InvalidBillNoException();

        double remainingLimit = limit - billRepository.findBillByFirstNameAndLastNameAndEmail(bill.firstName(),
                                                                                              bill.lastName(),
                                                                                              bill.email())
                                                      .stream()
                                                      .map(Bill::getAmount)
                                                      .reduce(0.0, Double::sum);

        var savedBill = billRepository.save(Bill.builder()
                                                .billNo(billNoUpperCase)
                                                .firstName(bill.firstName())
                                                .lastName(bill.lastName())
                                                .email(bill.email())
                                                .amount(bill.amount())
                                                .productName(bill.productName())
                                                .billStatus(bill.amount() > remainingLimit ? BillStatus.REJECTED : BillStatus.ACCEPTED)
                                                .build());


        if (bill.amount() > remainingLimit) throw new LimitExceededException();

        return new BillDTO(new BillDomain(savedBill.getBillNo(),
                                          savedBill.getFirstName(),
                                          savedBill.getLastName(),
                                          savedBill.getEmail(),
                                          savedBill.getAmount(),
                                          savedBill.getProductName()), savedBill.getBillStatus());
    }

    public BillDTO getBill(String billNo) {
        log.info("BS - processing getBill service.");

        var billNoUpperCase = billNo.toUpperCase();

        if (!billNoUpperCase.startsWith("TR")) throw new InvalidBillNoException();

        var bill = billRepository.findBillByBillNo(billNo).orElseThrow(BillNotFoundException::new);

        return new BillDTO(new BillDomain(bill.getBillNo(),
                                          bill.getFirstName(),
                                          bill.getLastName(),
                                          bill.getEmail(),
                                          bill.getAmount(),
                                          bill.getProductName()), bill.getBillStatus());
    }

    public List<BillDTO> getAcceptedBills() {
        log.info("BS - processing getAcceptedBills service.");

        var bills = billRepository.findBillByBillStatus(BillStatus.ACCEPTED);

        return bills.stream()
                    .map(bill -> new BillDTO(new BillDomain(bill.getBillNo(),
                                                            bill.getFirstName(),
                                                            bill.getLastName(),
                                                            bill.getEmail(),
                                                            bill.getAmount(),
                                                            bill.getProductName()), bill.getBillStatus()))
                    .collect(Collectors.toList());
    }

    public List<BillDTO> getRejectedBills() {
        log.info("BS - processing getRejectedBills service.");

        var bills = billRepository.findBillByBillStatus(BillStatus.REJECTED);

        return bills.stream()
                    .map(bill -> new BillDTO(new BillDomain(bill.getBillNo(),
                                                            bill.getFirstName(),
                                                            bill.getLastName(),
                                                            bill.getEmail(),
                                                            bill.getAmount(),
                                                            bill.getProductName()), bill.getBillStatus()))
                    .collect(Collectors.toList());
    }

}
