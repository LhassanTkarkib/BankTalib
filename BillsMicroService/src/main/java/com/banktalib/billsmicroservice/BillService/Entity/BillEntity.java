package com.banktalib.billsmicroservice.BillService.Entity;


import com.banktalib.billsmicroservice.BillService.Enums.PayementStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Bill")
public class BillEntity {
    @Id
    @SequenceGenerator(
            name = "Bill_sequence",
            sequenceName = "Bill_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Bill_sequence")
    private Long idBill;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "dueDate")
    private Date dueDate;

    @Column(name = "payementStatus")
    @Enumerated (EnumType.STRING)
    private PayementStatus payementStatus;

    //who owns the bill
    @Column(name = "accountNumberInitiated")
    private String accountNumberInitiated;

    //who will pay
    @Column(name = "accountNumbersInvolved")
    private String accountNumberInvolved;
}
