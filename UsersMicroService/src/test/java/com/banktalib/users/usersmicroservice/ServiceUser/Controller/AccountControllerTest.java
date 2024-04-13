package com.banktalib.users.usersmicroservice.ServiceUser.Controller;
import com.banktalib.users.usersmicroservice.ServiceUser.Controller.AccountController;
import com.banktalib.users.usersmicroservice.ServiceUser.Dto.AccountDto;
import com.banktalib.users.usersmicroservice.ServiceUser.Service.IAccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAccountService accountService;

    private AccountDto accountDto;

    @BeforeEach
    public void setUp() {
        accountDto = new AccountDto();
        accountDto.setAccountNumber("123456");
    }

    @Test
    public void testGetAllAccounts() throws Exception {
        List<AccountDto> accountDtoList = Collections.singletonList(accountDto);
        when(accountService.getAllAccounts()).thenReturn(accountDtoList);

        mockMvc.perform(get("/api/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idAccount").value(accountDto.getIdAccount()))
                .andExpect(jsonPath("$[0].accountNumber").value(accountDto.getAccountNumber()));
    }

    @Test
    public void testGetAccountById() throws Exception {
        when(accountService.getAccount(anyLong())).thenReturn(accountDto);

        mockMvc.perform(get("/api/v1/accounts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idAccount").value(accountDto.getIdAccount()))
                .andExpect(jsonPath("$.accountNumber").value(accountDto.getAccountNumber()));
    }

    @Test
    public void testCreateAccount() throws Exception {
        when(accountService.createAccount(any())).thenReturn(accountDto);

        mockMvc.perform(post("/api/v1/accounts/createAccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idAccount\":1,\"accountNumber\":\"123456\",\"balance\":1000.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idAccount").value(accountDto.getIdAccount()))
                .andExpect(jsonPath("$.accountNumber").value(accountDto.getAccountNumber()));
    }

    @Test
    public void testUpdateAccount() throws Exception {
        when(accountService.updateAccount(anyLong(), any())).thenReturn(accountDto);

        mockMvc.perform(put("/api/v1/accounts/updateAccount/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idAccount\":1,\"accountNumber\":\"123456\",\"balance\":1000.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idAccount").value(accountDto.getIdAccount()))
                .andExpect(jsonPath("$.accountNumber").value(accountDto.getAccountNumber()));
    }

    @Test
    public void testDeleteAccount() throws Exception {
        doNothing().when(accountService).deleteAccount(anyLong());

        mockMvc.perform(delete("/api/v1/accounts/deleteAccount/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @AfterEach
    public void tearDown() {
        accountDto = null;
    }
}