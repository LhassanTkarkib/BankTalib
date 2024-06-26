package com.banktalib.paymentservice.PaymentService.Service.Implimenetation;

import com.banktalib.UserClient.AccountClient;
import com.banktalib.UserClient.AccountDto;
import com.banktalib.paymentservice.PaymentService.Entity.TransactionEntity;
import com.banktalib.paymentservice.PaymentService.Enums.TransactionType;
import com.banktalib.paymentservice.PaymentService.Producer.PayementProducer;
import com.banktalib.paymentservice.PaymentService.Repository.TransactionRepository;
import com.banktalib.paymentservice.PaymentService.Service.IWithdrawalService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class WithdrawalService implements IWithdrawalService {

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PayementProducer payementProducer;

    @Override
    @Transactional
    public void cashWithdrawal(String accountNumber, double amount) {

        AccountDto account = accountClient.getAccountByAccountNumber(accountNumber);
        if (account == null) {
            throw new NotFoundException("Account not found");
        }


        double currentBalance = account.getBalance();
        if (currentBalance < amount) {
            throw new NotFoundException("Insufficient balance");
        }

        double newBalance = currentBalance - amount;
        account.setBalance(newBalance);
        accountClient.updateAccount(account.getIdAccount(), account);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setAmount(amount);
        transaction.setTypeTransaction(TransactionType.CASH_WITHDRAWAL);
        transaction.setDateTransaction(new Date());
        transaction.setSenderAccountNumber(account.getAccountNumber());
        transactionRepository.save(transaction);

        com.banktalib.UserClient.TransactionDto transactionDto1 = new com.banktalib.UserClient.TransactionDto();

        transactionDto1.setAmount(transaction.getAmount());
        transactionDto1.setSenderAccountNumber(transaction.getSenderAccountNumber());
        String type = transaction.getTypeTransaction().toString();
        transactionDto1.setDateTransaction(transaction.getDateTransaction());
        transactionDto1.setTypeTransaction(com.banktalib.UserClient.TransactionType.valueOf(type));


        payementProducer.sendMessage(transactionDto1);
    }


}
