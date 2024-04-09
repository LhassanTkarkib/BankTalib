package com.banktalib.paymentservice.PaymentService.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferDto {

    private String sourceAccountNumber;
    private String targetAccountNumber;
    private double amount;

}
