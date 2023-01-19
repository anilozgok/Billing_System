package com.anilcan.billing_system.controller;

import com.anilcan.billing_system.exception.BillRejectedException;
import com.anilcan.billing_system.model.entity.Bill;
import com.anilcan.billing_system.model.request.NewBillRequest;
import com.anilcan.billing_system.model.response.BillAcceptedResponse;
import com.anilcan.billing_system.model.response.BillListResponse;
import com.anilcan.billing_system.model.response.BillResponse;
import com.anilcan.billing_system.service.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/bill")
@Validated
public class BillController {

    private final Logger logger = LoggerFactory.getLogger(BillController.class);

    private BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/save")
    public ResponseEntity<BillAcceptedResponse<Bill>> saveBill(@RequestBody NewBillRequest newBillRequest) throws BillRejectedException {
        logger.info(" BC - saveBill request caught with NewBillRequest: " + newBillRequest.toString());
        Bill bill=billService.saveBill(newBillRequest);
        return ResponseEntity.ok(new BillAcceptedResponse<>("Bill successfully saved.", bill));
    }

    @GetMapping("/get/{billNo}")
    public BillResponse getBill(@PathVariable("billNo") Long billNo) {
        logger.info("BC - getBill request caught with billNo: " + billNo);
        Bill bill=billService.getBill(billNo);
        return new BillResponse(bill.getBillNo(),
                                bill.getFirstName(),
                                bill.getLastName(),
                                bill.getAmount(),
                                bill.getProductName());
    }

    @GetMapping("/get-accepted")
    public BillListResponse getAcceptedBills() { //object list of BillResponse
        logger.info("BC - getAcceptedBill request caught.");
        BillListResponse billListResponse = new BillListResponse(billService.getAcceptedBills());
        return billListResponse;
    }

    @GetMapping("/get-rejected")
    public BillListResponse getRejectedBills() {
        logger.info("BC - getRejectedBill request caught.");
        BillListResponse billListResponse = new BillListResponse(billService.getRejectedBills());
        return billListResponse;
    }
}
