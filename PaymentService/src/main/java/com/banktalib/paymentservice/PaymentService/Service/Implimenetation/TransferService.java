package com.banktalib.paymentservice.PaymentService.Service.Implimenetation;

import com.banktalib.UserClient.AccountClient;
import com.banktalib.UserClient.AccountDto;
import com.banktalib.paymentservice.PaymentService.Dto.TransactionDto;
import com.banktalib.paymentservice.PaymentService.Entity.TransactionEntity;
import com.banktalib.paymentservice.PaymentService.Enums.TransactionType;
import com.banktalib.paymentservice.PaymentService.Mapper.TransactionMapper;
import com.banktalib.paymentservice.PaymentService.Repository.TransactionRepository;
import com.banktalib.paymentservice.PaymentService.Service.ITransferService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TransferService implements ITransferService {

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    @Transactional
    public TransactionDto fundTransfer(String sourceAccountNumber, String targetAccountNumber, double amount) {
        AccountDto sourceAccount = accountClient.getAccountByAccountNumber(sourceAccountNumber);
        if (sourceAccount == null) {
            throw new NotFoundException("Source account not found");
        }

        AccountDto targetAccount = accountClient.getAccountByAccountNumber(targetAccountNumber);
        if (targetAccount == null) {
            throw new NotFoundException("Target account not found");
        }

        double sourceBalance = sourceAccount.getBalance();
        if (sourceBalance < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        double newSourceBalance = sourceBalance - amount;
        sourceAccount.setBalance(newSourceBalance);
        accountClient.updateAccount(sourceAccount.getIdAccount(), sourceAccount);

        double targetBalance = targetAccount.getBalance();
        double newTargetBalance = targetBalance + amount;
        targetAccount.setBalance(newTargetBalance);
        accountClient.updateAccount(targetAccount.getIdAccount(), targetAccount);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setAmount(amount);
        transaction.setTypeTransaction(TransactionType.CASH_TRANSFER);
        transaction.setDateTransaction(new Date());
        transaction.setSenderAccountNumber(sourceAccount.getAccountNumber());
        transaction.setReceiverAccountNumber(targetAccount.getAccountNumber());
        TransactionDto transactiondto = transactionMapper.toDto(transactionRepository.save(transaction));
        return transactiondto;
    }

}
