package com.banktalib.paymentservice.PaymentService.Service.Implimenetation;

import com.banktalib.UserClient.AccountClient;
import com.banktalib.UserClient.AccountDto;
import com.banktalib.paymentservice.PaymentService.Dto.TransactionDto;
import com.banktalib.paymentservice.PaymentService.Entity.TransactionEntity;
import com.banktalib.paymentservice.PaymentService.Enums.TransactionType;
import com.banktalib.paymentservice.PaymentService.Mapper.TransactionMapper;
import com.banktalib.paymentservice.PaymentService.Producer.PayementProducer;
import com.banktalib.paymentservice.PaymentService.Repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TransferServiceTest {

    @InjectMocks
    private TransferService transferService;

    @Mock
    private AccountClient accountClient;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private PayementProducer payementProducer;

    @Mock
    private TransactionMapper transactionMapper;

    private AccountDto sourceAccountDto;
    private AccountDto targetAccountDto;
    private TransactionEntity transactionEntity;
    private TransactionDto transactionDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        sourceAccountDto = new AccountDto();
        sourceAccountDto.setAccountNumber("123456");
        sourceAccountDto.setBalance(1000.0);

        targetAccountDto = new AccountDto();
        targetAccountDto.setAccountNumber("654321");
        targetAccountDto.setBalance(500.0);

        transactionDto = new TransactionDto();
        transactionDto.setAmount(500.0);
        transactionDto.setSenderAccountNumber(sourceAccountDto.getAccountNumber());
        transactionDto.setReceiverAccountNumber(targetAccountDto.getAccountNumber());
        transactionDto.setTypeTransaction(TransactionType.CASH_TRANSFER);
        transactionDto.setDateTransaction(new Date());

        transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(500.0);
        transactionEntity.setSenderAccountNumber(sourceAccountDto.getAccountNumber());
        transactionEntity.setReceiverAccountNumber(targetAccountDto.getAccountNumber());
        transactionEntity.setTypeTransaction(TransactionType.CASH_TRANSFER);
        transactionEntity.setDateTransaction(new Date());
    }

    @Test
    public void testFundTransfer() {
        when(accountClient.getAccountByAccountNumber(sourceAccountDto.getAccountNumber())).thenReturn(sourceAccountDto);
        when(accountClient.getAccountByAccountNumber(targetAccountDto.getAccountNumber())).thenReturn(targetAccountDto);
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(transactionEntity);
        when(transactionMapper.toDto(any(TransactionEntity.class))).thenReturn(transactionDto);

        TransactionDto result = transferService.fundTransfer(sourceAccountDto.getAccountNumber(), targetAccountDto.getAccountNumber(), 500.0);

        verify(accountClient, times(1)).getAccountByAccountNumber(sourceAccountDto.getAccountNumber());
        verify(accountClient, times(1)).getAccountByAccountNumber(targetAccountDto.getAccountNumber());
        verify(transactionRepository, times(1)).save(any(TransactionEntity.class));
        verify(payementProducer, times(1)).sendMessage(any(com.banktalib.UserClient.TransactionDto.class));

        assertEquals(transactionDto.getAmount(), result.getAmount());
        assertEquals(transactionDto.getSenderAccountNumber(), result.getSenderAccountNumber());
        assertEquals(transactionDto.getReceiverAccountNumber(), result.getReceiverAccountNumber());
    }
}