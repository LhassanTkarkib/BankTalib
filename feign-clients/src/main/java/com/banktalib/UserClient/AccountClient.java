package com.banktalib.UserClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "accountClient", url = "http://localhost:5050")
public interface AccountClient {

    @GetMapping("/api/v1/accounts/getAccountByAccountNumber/{accountNumber}")
    AccountDto getAccountByAccountNumber(@PathVariable String accountNumber);

    @GetMapping("/api/v1/accounts/{accountId}")
    AccountDto getAccountById(@PathVariable Long accountId);

    @GetMapping("/api/v1/accounts")
    List<AccountDto> getAllAccounts();

    @GetMapping("/getAccountByUserName/{username}")
     AccountDto getAccountByUsername(@PathVariable String username);

    @PostMapping("/api/v1/accounts/createAccount")
    AccountDto createAccount(@RequestBody AccountDto accountDto);

    @PutMapping("/api/v1/accounts/updateAccount/{accountId}")
    AccountDto updateAccount(@PathVariable Long accountId, @RequestBody AccountDto accountDto);



    @DeleteMapping("/api/v1/accounts/deleteAccount/{accountId}")
    void deleteAccount(@PathVariable Long accountId);
}