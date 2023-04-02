package com.anilcan.billing_system.model.entity;

import com.anilcan.billing_system.enums.BillStatus;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String billNo;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BillStatus billStatus;

}