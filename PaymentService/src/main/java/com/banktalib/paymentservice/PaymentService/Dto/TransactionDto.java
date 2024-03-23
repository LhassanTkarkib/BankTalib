package com.banktalib.paymentservice.PaymentService.Dto;

import com.banktalib.paymentservice.PaymentService.Entity.TransactionEntity;
import com.banktalib.paymentservice.PaymentService.Enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link TransactionEntity}
 */
@AllArgsConstructor
@Getter
public class TransactionDto implements Serializable {
    private final Long idTransaction;
    private final double amount;
    private final Date dateTransaction;
    private final TransactionType typeTransaction;
    private final long idSenderAccount;
    private final long idReceiverAccount;
}