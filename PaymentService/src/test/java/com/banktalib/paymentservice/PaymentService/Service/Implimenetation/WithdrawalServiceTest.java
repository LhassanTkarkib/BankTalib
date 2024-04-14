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
public class WithdrawalServiceTest {

    @InjectMocks
    private WithdrawalService withdrawalService;

    @Mock
    private AccountClient accountClient;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private PayementProducer payementProducer;

    @Mock
    private TransactionMapper transactionMapper;

    private AccountDto accountDto;
    private TransactionEntity transactionEntity;
    private TransactionDto transactionDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        accountDto = new AccountDto();
        accountDto.setAccountNumber("123456");
        accountDto.setBalance(1000.0);

        transactionDto = new TransactionDto();
        transactionDto.setAmount(500.0);
        transactionDto.setSenderAccountNumber(accountDto.getAccountNumber());
        transactionDto.setTypeTransaction(TransactionType.CASH_WITHDRAWAL);
        transactionDto.setDateTransaction(new Date());

        transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(500.0);
        transactionEntity.setSenderAccountNumber(accountDto.getAccountNumber());
        transactionEntity.setTypeTransaction(TransactionType.CASH_WITHDRAWAL);
        transactionEntity.setDateTransaction(new Date());
    }

    @Test
    public void testCashWithdrawal() {
        when(accountClient.getAccountByAccountNumber(accountDto.getAccountNumber())).thenReturn(accountDto);
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(transactionEntity);
        when(transactionMapper.toDto(any(TransactionEntity.class))).thenReturn(transactionDto);

        withdrawalService.cashWithdrawal(accountDto.getAccountNumber(), 500.0);

        verify(accountClient, times(1)).getAccountByAccountNumber(accountDto.getAccountNumber());
        verify(transactionRepository, times(1)).save(any(TransactionEntity.class));
        verify(payementProducer, times(1)).sendMessage(any(com.banktalib.UserClient.TransactionDto.class));
    }
}