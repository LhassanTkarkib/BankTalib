package com.banktalib.users.usersmicroservice.ServiceUser.Controller;
import com.banktalib.users.usersmicroservice.ServiceUser.Dto.AccountDto;
import com.banktalib.users.usersmicroservice.ServiceUser.Service.IAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AccountControllerTest {

    @InjectMocks
    AccountController accountController;

    @Mock
    IAccountService accountService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllAccountsReturnsAccountList() {
        AccountDto accountDto = new AccountDto();
        List<AccountDto> accountDtoList = Arrays.asList(accountDto);

        when(accountService.getAllAccounts()).thenReturn(accountDtoList);

        ResponseEntity<List<AccountDto>> response = accountController.getAllAccounts();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(accountDtoList, response.getBody());
    }

    @Test
    public void getAccountByIdReturnsAccount() {
        AccountDto accountDto = new AccountDto();
        Long accountId = 1L;

        when(accountService.getAccount(accountId)).thenReturn(accountDto);

        ResponseEntity<AccountDto> response = accountController.getAccountById(accountId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(accountDto, response.getBody());
    }

    @Test
    public void getAccountByAccountNumberReturnsAccount() {
        AccountDto accountDto = new AccountDto();
        String accountNumber = "123456";

        when(accountService.getAccountByAccountNumber(accountNumber)).thenReturn(accountDto);

        ResponseEntity<AccountDto> response = accountController.getAccountByAccountNumber(accountNumber);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(accountDto, response.getBody());
    }

    @Test
    public void getAccountByUsernameReturnsAccount() {
        AccountDto accountDto = new AccountDto();
        String username = "testUser";

        when(accountService.getAccountByUserName(username)).thenReturn(accountDto);

        ResponseEntity<AccountDto> response = accountController.getAccountByUsername(username);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(accountDto, response.getBody());
    }

    @Test
    public void createAccountReturnsCreatedAccount() {
        AccountDto accountDto = new AccountDto();

        when(accountService.createAccount(accountDto)).thenReturn(accountDto);

        ResponseEntity<AccountDto> response = accountController.createAccount(accountDto);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(accountDto, response.getBody());
    }

    @Test
    public void updateAccountReturnsUpdatedAccount() {
        AccountDto accountDto = new AccountDto();
        Long accountId = 1L;

        when(accountService.updateAccount(accountId, accountDto)).thenReturn(accountDto);

        ResponseEntity<AccountDto> response = accountController.updateAccount(accountId, accountDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(accountDto, response.getBody());
    }

    @Test
    public void deleteAccountReturnsOkStatus() {
        Long accountId = 1L;

        ResponseEntity<Void> response = accountController.deleteAccount(accountId);

        assertEquals(200, response.getStatusCodeValue());
    }
}