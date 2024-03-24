package com.banktalib.paymentservice.PaymentService.Service;

import com.banktalib.paymentservice.PaymentService.Dto.TransactionDto;

import java.util.List;

public interface ITransactionService {

    List<TransactionDto> getAllTransactions();

    TransactionDto getTransaction(Long id);

    TransactionDto createTransaction(TransactionDto transactionDto);

    TransactionDto updateTransaction(Long id, TransactionDto transactionDto);

    void deleteTransaction(Long id);
}