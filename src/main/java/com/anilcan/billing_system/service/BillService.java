package com.anilcan.billing_system.service;

import com.anilcan.billing_system.controller.BillController;
import com.anilcan.billing_system.exception.BillNotFoundException;
import com.anilcan.billing_system.exception.BillRejectedException;
import com.anilcan.billing_system.model.entity.Bill;
import com.anilcan.billing_system.model.request.NewBillRequest;
import com.anilcan.billing_system.model.response.BillResponse;
import com.anilcan.billing_system.repository.BillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    private final Logger logger = LoggerFactory.getLogger(BillService.class);
    private BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Value("${BillingSystem.totalBillLimit}")
    private double limit;

    public Bill saveBill(NewBillRequest newBillRequest) throws BillRejectedException {
        logger.info("BS - processing request. NewBillRequest: " + newBillRequest);

        double sumOfBills = billRepository.getSumOfBills(newBillRequest.getFirstName(), newBillRequest.getLastName(), newBillRequest.getEmail());

        Boolean billStatus = (limit > sumOfBills) ? true : false;

        Bill bill = billRepository.save(new Bill(
                newBillRequest.getBillNo(),
                newBillRequest.getFirstName(),
                newBillRequest.getLastName(),
                newBillRequest.getEmail(),
                newBillRequest.getAmount(),
                newBillRequest.getProductName(),
                billStatus
        ));

        if (!billStatus)
            throw new BillRejectedException(bill.getBillNo());

        return bill;

    }

    public Bill getBill(Long billNo) throws BillNotFoundException {
        logger.info("BS - processing getBill service.");
        Bill bill = billRepository.getReferenceById(billNo);

        if (bill == null)
            throw new BillRejectedException(billNo);

        return bill;
    }

    public List<Bill> getAcceptedBills() throws BillNotFoundException {
        logger.info("BS - processing getAcceptedBills service.");
        List<Bill> bills = billRepository.getAcceptedBills();
        if (bills.size() == 0)
            throw new BillNotFoundException("There are no accepted bills on the system.");
        return bills;
    }

    public List<Bill> getRejectedBills() {
        logger.info("BS - processing getRejectedBills service.");
        List<Bill> bills = billRepository.getRejectedBills();
        if (bills.size() == 0)
            throw new BillNotFoundException("There are no rejected bills on the system.");
        return bills;

    }

}
