package com.banktalib.paymentservice.PaymentService.Service.Implimenetation;

import com.banktalib.UserClient.AccountClient;
import com.banktalib.UserClient.AccountDto;
import com.banktalib.paymentservice.PaymentService.Entity.TransactionEntity;
import com.banktalib.paymentservice.PaymentService.Enums.TransactionType;
import com.banktalib.paymentservice.PaymentService.Repository.TransactionRepository;
import com.banktalib.paymentservice.PaymentService.Service.IDepositService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DepositService implements IDepositService {

    @Autowired
    private AccountClient accountClient;


    @Autowired
    private TransactionRepository transactionRepository;

    public void cashDeposit(String username, double amount) {

        AccountDto account = accountClient.getAccountByUsername(username);

        if (account == null) {
            throw new NotFoundException("Account not found");
        }

        double currentBalance = account.getBalance();
        double newBalance = currentBalance + amount;
        account.setBalance(newBalance);
        accountClient.updateAccount(account.getIdAccount(), account);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setAmount(amount);
        transaction.setTypeTransaction(TransactionType.CASH_DEPOSIT);
        transaction.setDateTransaction(new Date());
        transaction.setIdSenderAccount(account.getIdAccount());
        transactionRepository.save(transaction);
    }
}
