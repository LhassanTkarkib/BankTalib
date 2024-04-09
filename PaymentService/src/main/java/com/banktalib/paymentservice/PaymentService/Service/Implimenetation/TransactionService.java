package com.banktalib.paymentservice.PaymentService.Service.Implimenetation;

import com.banktalib.paymentservice.PaymentService.Dto.TransactionDto;
import com.banktalib.paymentservice.PaymentService.Entity.TransactionEntity;
import com.banktalib.paymentservice.PaymentService.Mapper.TransactionMapper;
import com.banktalib.paymentservice.PaymentService.Repository.TransactionRepository;
import com.banktalib.paymentservice.PaymentService.Service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;


    @Override
    public List<TransactionDto> getAllTransactionsByAccountNumber(String accountNumber) {
        List<TransactionEntity> transactions = transactionRepository.findAllBySenderAccountNumber(accountNumber);
        return transactions.stream().map(transactionMapper::toDto).collect(Collectors.toList());
    }


    @Override
    public TransactionDto getTransaction(Long id) {
        TransactionEntity transaction = transactionRepository.findById(id).get();
        return transactionMapper.toDto(transaction);
    }

    @Override
    public TransactionDto updateTransaction(long id, TransactionDto transactionDto) {
        TransactionEntity existingTransaction = transactionRepository.findById(id).get();
        transactionMapper.partialUpdate(existingTransaction, transactionDto);
        TransactionEntity savedTransaction = transactionRepository.save(existingTransaction);
        return transactionMapper.toDto(savedTransaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

}
