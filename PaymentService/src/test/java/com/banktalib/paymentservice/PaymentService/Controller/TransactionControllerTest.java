package com.banktalib.paymentservice.PaymentService.Controller;

import com.banktalib.paymentservice.PaymentService.Dto.TransactionDto;
import com.banktalib.paymentservice.PaymentService.Service.ITransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITransactionService transactionService;

    @Test
    public void testGetAllTransactionsByAccountNumber() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(500.0);
        transactionDto.setSenderAccountNumber("123456");
        transactionDto.setDateTransaction(new Date());

        List<TransactionDto> transactions = Collections.singletonList(transactionDto);

        when(transactionService.getAllTransactionsByAccountNumber("123456")).thenReturn(transactions);

        mockMvc.perform(get("/api/v1/transactions/{accountNumber}", "123456")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].amount").value(transactionDto.getAmount()))
                .andExpect(jsonPath("$[0].senderAccountNumber").value(transactionDto.getSenderAccountNumber()));
    }

}