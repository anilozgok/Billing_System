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
    private final double limit;

    public BillDTO saveBill(BillDTO bill) {
        log.info("BS - processing NewBillRequest.");
        var billStatus = BillStatus.ACCEPTED;

        if (!bill.info().billNo().startsWith("TR") || !bill.info().billNo().startsWith("tr")) throw new InvalidBillNoException();

        var totalAmount = billRepository.findBillByFirstNameAndLastNameAndEmail(bill.info().firstName(),
                                                                                bill.info().lastName(),
                                                                                bill.info().email())
                                        .stream()
                                        .map(Bill::getAmount)
                                        .reduce(0.0, Double::sum);

        if (totalAmount >= limit) {
            billStatus = BillStatus.REJECTED;
            throw new LimitExceededException();
        }

        var savedBill = billRepository.save(Bill.builder()
                                                .billNo(bill.info().billNo())
                                                .firstName(bill.info().firstName())
                                                .lastName(bill.info().lastName())
                                                .email(bill.info().email())
                                                .amount(bill.info().amount())
                                                .productName(bill.info().productName())
                                                .billStatus(billStatus)
                                                .build());

        return new BillDTO(new BillDomain(savedBill.getBillNo(),
                                          savedBill.getFirstName(),
                                          savedBill.getLastName(),
                                          savedBill.getEmail(),
                                          savedBill.getAmount(),
                                          savedBill.getProductName()), savedBill.getBillStatus());
    }


    public BillDTO getBill(String billNo) {
        log.info("BS - processing getBill service.");

        if (!billNo.startsWith("TR") || !billNo.startsWith("tr")) throw new InvalidBillNoException();

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
