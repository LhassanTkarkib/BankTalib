package com.banktalib.paymentservice.PaymentService.Service;

import com.banktalib.paymentservice.PaymentService.Dto.TransactionDto;

import java.util.List;

public interface ITransactionService {

    TransactionDto saveTransaction(TransactionDto transactionDto);

    List<TransactionDto> getAllTransactionsByAccountNumber(String accountNumber);

    TransactionDto getTransaction(Long id);


    TransactionDto updateTransaction(long id, TransactionDto transactionDto);

    void deleteTransaction(Long id);
}