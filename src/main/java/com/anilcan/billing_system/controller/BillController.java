package com.anilcan.billing_system.controller;

import com.anilcan.billing_system.exception.BillNotFoundException;
import com.anilcan.billing_system.exception.BillRejectedException;
import com.anilcan.billing_system.model.entity.Bill;
import com.anilcan.billing_system.model.request.NewBillRequest;
import com.anilcan.billing_system.model.response.BillAcceptedResponse;
import com.anilcan.billing_system.model.response.BillListResponse;
import com.anilcan.billing_system.model.response.BillResponse;
import com.anilcan.billing_system.service.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<BillAcceptedResponse> saveBill(@RequestBody NewBillRequest newBillRequest) {
        logger.info(" BC - saveBill service caught with NewBillRequest: " + newBillRequest.toString());

        Bill bill=billService.saveBill(newBillRequest);
        return new ResponseEntity<>(new BillAcceptedResponse("Bill saved successfully.", bill), HttpStatus.OK);
    }

    @GetMapping("/get/{billNo}")
    public ResponseEntity<BillResponse> getBill(@PathVariable("billNo") Long billNo) {
        logger.info("BC - getBill service caught with billNo: " + billNo);
        Bill bill=billService.getBill(billNo);
        BillResponse response = new BillResponse(bill.getBillNo(),
                                bill.getFirstName(),
                                bill.getLastName(),
                                bill.getAmount(),
                                bill.getProductName());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-accepted")
    public ResponseEntity<BillListResponse> getAcceptedBills() { //object list of BillResponse
        logger.info("BC - getAcceptedBill service caught.");
        BillListResponse billListResponse = new BillListResponse(billService.getAcceptedBills());
        return new ResponseEntity<>(billListResponse, HttpStatus.OK);
    }

    @GetMapping("/get-rejected")
    public ResponseEntity<BillListResponse> getRejectedBills() {
        logger.info("BC - getRejectedBill service caught.");
        BillListResponse billListResponse = new BillListResponse(billService.getRejectedBills());
        return new ResponseEntity<>(billListResponse, HttpStatus.OK);
    }
}
