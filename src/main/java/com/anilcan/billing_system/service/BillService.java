package com.anilcan.billing_system.service;

import com.anilcan.billing_system.model.entity.Bill;
import com.anilcan.billing_system.model.request.NewBillRequest;
import com.anilcan.billing_system.model.response.BillResponse;
import com.anilcan.billing_system.repository.BillRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    private BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Value("${BillingSystem.totalBillLimit}")
    private double limit;

    public Bill saveBill(NewBillRequest newBillRequest) {

        /*
         * get sum of bills for the specialist
         * firstName, lastName, email
         * select sum(amount) from Bill where firstName=firstName, lastName=lastName, email=email => named query atılabilir
         *
         * check if result of the query above exceeds limit if exceeds billStatus must be false otherwise it's must be true
         * */

        double sumOfBills = billRepository.getSumOfBills(newBillRequest.getFirstName(), newBillRequest.getLastName(), newBillRequest.getEmail());

        Boolean billStatus = (limit > sumOfBills) ? true : false;

        Bill bill = new Bill(
                newBillRequest.getBillNo(),
                newBillRequest.getFirstName(),
                newBillRequest.getLastName(),
                newBillRequest.getEmail(),
                newBillRequest.getAmount(),
                newBillRequest.getProductName(),
                billStatus
        );

        return billRepository.save(bill);
    }

    public Bill getBill(Long billNo){

        return billRepository.getReferenceById(billNo);
    }

    public List<Bill> getAcceptedBills(){
        return billRepository.getAcceptedBills();
    }

    public List<Bill> getRejectedBills(){
        return billRepository.getRejectedBills();
    }

}
