package com.banktalib.paymentservice.PaymentService.Service;
public interface IWithdrawalService {
    void cashWithdrawal(String accountNumber, double amount);
}