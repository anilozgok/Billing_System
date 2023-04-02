package com.anilcan.billing_system.controller;

import com.anilcan.billing_system.model.request.NewBillRequest;
import com.anilcan.billing_system.model.response.BillListResponse;
import com.anilcan.billing_system.model.response.BillResponse;
import com.anilcan.billing_system.service.BillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bill")
public class BillController {

    private final BillService billService;

    @PostMapping("/save")
    public ResponseEntity<BillResponse> saveBill(@RequestBody NewBillRequest newBillRequest) {
        log.info(" BC - saveBill service caught with NewBillRequest: " + newBillRequest.toString());

        var bill = billService.saveBill(newBillRequest.bill());

        return new ResponseEntity<>(new BillResponse(bill), HttpStatus.OK);
    }

    @GetMapping("/get/{billNo}")
    public ResponseEntity<BillResponse> getBill(@PathVariable("billNo") String billNo) {
        log.info("BC - getBill service caught with billNo: " + billNo);

        var bill = billService.getBill(billNo);

        return new ResponseEntity<>(new BillResponse(bill), HttpStatus.OK);
    }

    @GetMapping("/get-accepted")
    public ResponseEntity<BillListResponse> getAcceptedBills() { //object list of BillResponse
        log.info("BC - getAcceptedBills service caught.");

        var bills = billService.getAcceptedBills();

        return new ResponseEntity<>(new BillListResponse(bills), HttpStatus.OK);

    }

    @GetMapping("/get-rejected")
    public ResponseEntity<BillListResponse> getRejectedBills() {
        log.info("BC - getRejectedBills service caught.");

        var bills = billService.getRejectedBills();

        return new ResponseEntity<>(new BillListResponse(bills), HttpStatus.OK);


    }

}
