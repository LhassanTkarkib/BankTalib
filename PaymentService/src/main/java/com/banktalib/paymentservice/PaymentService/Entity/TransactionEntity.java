package com.banktalib.paymentservice.PaymentService.Entity;


import com.banktalib.paymentservice.PaymentService.Enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_sequence")
    private Long idTransaction;

    private Double amount;

    @Column(name = "DateTransaction")
    private Date dateTransaction;

    @Column(name = "TypeTransaction")
    private TransactionType typeTransaction;

    @Column(name = "idSenderAccount")
    private Long idSenderAccount;

    @Column(name = "idReceiverAccount")
    private Long idReceiverAccount;


}
