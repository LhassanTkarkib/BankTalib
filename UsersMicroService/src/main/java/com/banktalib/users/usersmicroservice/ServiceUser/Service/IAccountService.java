package com.banktalib.users.usersmicroservice.ServiceUser.Service;

import com.banktalib.users.usersmicroservice.ServiceUser.Dto.AccountDto;
import java.util.List;

public interface IAccountService {

    AccountDto createAccount(AccountDto account);

    AccountDto getAccount(Long accountId);

    AccountDto getAccountByAccountNumber(String accountNumber);

    List<AccountDto> getAllAccounts();

    AccountDto updateAccount(Long accountId, AccountDto accountDto);

    void deleteAccount(Long accountId);
}