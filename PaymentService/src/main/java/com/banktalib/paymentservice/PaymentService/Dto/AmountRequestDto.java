package com.banktalib.paymentservice.PaymentService.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmountRequestDto implements Serializable {
    private String accountNumber;
    private Double amount;

}
