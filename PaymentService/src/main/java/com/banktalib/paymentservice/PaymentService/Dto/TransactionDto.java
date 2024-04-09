package com.banktalib.paymentservice.PaymentService.Dto;

import com.banktalib.UserClient.AccountDto;
import com.banktalib.paymentservice.PaymentService.Entity.TransactionEntity;
import com.banktalib.paymentservice.PaymentService.Enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link TransactionEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto implements Serializable {
    private Long idTransaction;
    private Double amount;
    private Date dateTransaction;
    private TransactionType typeTransaction;
    private String senderAccountNumber;
    private String receiverAccountNumber;
}