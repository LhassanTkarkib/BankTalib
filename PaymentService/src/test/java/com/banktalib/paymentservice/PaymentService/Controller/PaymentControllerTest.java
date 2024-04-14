package com.banktalib.paymentservice.PaymentService.Controller;

import com.banktalib.paymentservice.PaymentService.Dto.AmountRequestDto;
import com.banktalib.paymentservice.PaymentService.Dto.TransferDto;
import com.banktalib.paymentservice.PaymentService.Service.IDepositService;
import com.banktalib.paymentservice.PaymentService.Service.ITransferService;
import com.banktalib.paymentservice.PaymentService.Service.IWithdrawalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDepositService depositService;

    @MockBean
    private IWithdrawalService withdrawalService;

    @MockBean
    private ITransferService transferService;

    @Test
    public void testCashDeposit() throws Exception {
        mockMvc.perform(post("/api/v1/transactions/deposit/{LoggedAccountNumber}", "123456")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\":100.0}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCashWithdrawal() throws Exception {
        mockMvc.perform(post("/api/v1/transactions/withdraw/{LoggedAccountNumber}", "123456")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\":100.0}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFundTransfer() throws Exception {
        mockMvc.perform(post("/api/v1/transactions/transfer/{LoggedAccountNumber}", "123456")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sourceAccountNumber\":\"123456\",\"targetAccountNumber\":\"654321\",\"amount\":100.0}"))
                .andExpect(status().isOk());
    }
}