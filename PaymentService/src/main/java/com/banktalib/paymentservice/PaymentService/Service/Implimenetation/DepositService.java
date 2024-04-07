package com.banktalib.paymentservice.PaymentService.Service.Implimenetation;

import com.banktalib.UserClient.AccountDto;
import com.banktalib.paymentservice.PaymentService.Entity.TransactionEntity;
import com.banktalib.paymentservice.PaymentService.Enums.TransactionType;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DepositService {
    public void cashDeposit(String accountNumber, String pin, double amount) {
//        AccountDto account = accountRepository.findByAccountNumber(accountNumber);
        AccountDto account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new NotFoundException("Account not found");
        }

        if (!passwordEncoder.matches(pin, account.getPin())) {
            throw new UnauthorizedException("Invalid PIN");
        }

        double currentBalance = account.getBalance();
        double newBalance = currentBalance + amount;
        account.setBalance(newBalance);
        accountRepository.save(account);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setAmount(amount);
        transaction.setTypeTransaction(TransactionType.CASH_DEPOSIT);
        transaction.setDateTransaction(new Date());
        transaction.setIdSenderAccount(account);
        transactionRepository.save(transaction);
    }
}
