package com.anilcan.billing_system.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Bill")
@Entity
@NamedQuery(name = "Bill.getRejectedBill", query = "SELECT b FROM Bill b where b.billStatus=false")
@NamedQuery(name = "Bill.getAcceptedBill", query = "SELECT b FROM Bill b where b.billStatus=true")
@NamedQuery(name = "Bill.getSumOfBills", query = "SELECT SUM(b.amount) FROM Bill b where b.firstName=:firstName AND b.lastName=:lastName AND b.email= :email")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billNo;
    private String firstName;
    private String lastName;
    private String email;
    private double amount;
    private String productName;
    private boolean billStatus;
}