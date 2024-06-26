package com.banktalib.paymentservice.PaymentService.Service.Implimenetation;

import com.banktalib.UserClient.AccountClient;
import com.banktalib.UserClient.AccountDto;

import com.banktalib.paymentservice.PaymentService.Dto.TransactionDto;
import com.banktalib.paymentservice.PaymentService.Entity.TransactionEntity;
import com.banktalib.paymentservice.PaymentService.Enums.TransactionType;
import com.banktalib.paymentservice.PaymentService.Mapper.TransactionMapper;
import com.banktalib.paymentservice.PaymentService.Producer.PayementProducer;
import com.banktalib.paymentservice.PaymentService.Repository.TransactionRepository;
import com.banktalib.paymentservice.PaymentService.Service.IDepositService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class DepositService implements IDepositService {

    private final AccountClient accountClient;
    private final TransactionRepository transactionRepository;
    private final PayementProducer payementProducer;
    private final TransactionMapper transactionMapper;

    public DepositService(AccountClient accountClient, TransactionRepository transactionRepository, PayementProducer payementProducer, TransactionMapper transactionMapper) {
        this.accountClient = accountClient;
        this.transactionRepository = transactionRepository;
        this.payementProducer = payementProducer;
        this.transactionMapper = transactionMapper;
    }

    @Override
    @Transactional
    public void cashDeposit(String accountNumber, double amount) {
        AccountDto account = accountClient.getAccountByAccountNumber(accountNumber);

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
        transaction.setSenderAccountNumber(account.getAccountNumber());

        TransactionDto transactionDto =transactionMapper.toDto(transactionRepository.save(transaction));

        com.banktalib.UserClient.TransactionDto transactionDto1 = new com.banktalib.UserClient.TransactionDto();

        transactionDto1.setAmount(transactionDto.getAmount());
        transactionDto1.setSenderAccountNumber(transactionDto.getSenderAccountNumber());
        String type = transactionDto.getTypeTransaction().toString();
        transactionDto1.setDateTransaction(transactionDto.getDateTransaction());
        transactionDto1.setTypeTransaction(com.banktalib.UserClient.TransactionType.valueOf(type));


        payementProducer.sendMessage(transactionDto1);
    }
}
