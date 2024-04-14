package com.banktalib.paymentservice.PaymentService.Service.Implimenetation;
import com.banktalib.paymentservice.PaymentService.Dto.TransactionDto;
import com.banktalib.paymentservice.PaymentService.Entity.TransactionEntity;
import com.banktalib.paymentservice.PaymentService.Mapper.TransactionMapper;
import com.banktalib.paymentservice.PaymentService.Repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    private TransactionDto transactionDto;
    private TransactionEntity transactionEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        transactionDto = new TransactionDto();
        transactionDto.setAmount(500.0);
        transactionDto.setSenderAccountNumber("123456");

        transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(500.0);
        transactionEntity.setSenderAccountNumber("123456");
    }

    @Test
    public void testSaveTransaction() {
        when(transactionMapper.toEntity(transactionDto)).thenReturn(transactionEntity);
        when(transactionRepository.save(transactionEntity)).thenReturn(transactionEntity);
        when(transactionMapper.toDto(transactionEntity)).thenReturn(transactionDto);

        TransactionDto result = transactionService.saveTransaction(transactionDto);

        assertEquals(transactionDto, result);
    }

    @Test
    public void testGetAllTransactionsByAccountNumber() {
        List<TransactionEntity> transactions = Collections.singletonList(transactionEntity);

        when(transactionRepository.findAllBySenderAccountNumber("123456")).thenReturn(transactions);
        when(transactionMapper.toDto(transactionEntity)).thenReturn(transactionDto);

        List<TransactionDto> result = transactionService.getAllTransactionsByAccountNumber("123456");

        assertEquals(1, result.size());
        assertEquals(transactionDto, result.get(0));
    }

    @Test
    public void testGetTransaction() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transactionEntity));
        when(transactionMapper.toDto(transactionEntity)).thenReturn(transactionDto);

        TransactionDto result = transactionService.getTransaction(1L);

        assertEquals(transactionDto, result);
    }



    @Test
    public void testDeleteTransaction() {
        doNothing().when(transactionRepository).deleteById(1L);

        transactionService.deleteTransaction(1L);

        verify(transactionRepository, times(1)).deleteById(1L);
    }
}