package com.banktalib.paymentservice.PaymentService.Service;

import com.banktalib.paymentservice.PaymentService.Dto.TransactionDto;

public interface ITransferService {
    TransactionDto fundTransfer(String sourceAccountNumber, String targetAccountNumber , double amount);
}