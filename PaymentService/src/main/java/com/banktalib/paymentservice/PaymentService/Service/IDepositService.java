package com.banktalib.paymentservice.PaymentService.Service;

public interface IDepositService {
    void cashDeposit(String accountNumber, double amount);
}