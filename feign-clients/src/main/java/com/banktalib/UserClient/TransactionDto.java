package com.banktalib.UserClient;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

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