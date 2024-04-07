package com.banktalib.users.usersmicroservice.ServiceUser.Service.Implimentation;

import com.banktalib.users.usersmicroservice.ServiceUser.Dto.AccountDto;
import com.banktalib.users.usersmicroservice.ServiceUser.Entity.AccountEntity;
import com.banktalib.users.usersmicroservice.ServiceUser.Entity.UserEntity;
import com.banktalib.users.usersmicroservice.ServiceUser.Mapper.AccountMapper;
import com.banktalib.users.usersmicroservice.ServiceUser.Repository.AccountRepository;
import com.banktalib.users.usersmicroservice.ServiceUser.Repository.UserRepository;
import com.banktalib.users.usersmicroservice.ServiceUser.Service.IAccountService;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    public AccountDto createAccount(AccountDto account){
        AccountEntity savedAccount = accountMapper.toEntity(account);
        savedAccount.setAccountNumber(generateAccountNumber());
        return accountMapper.toDto(accountRepository.save(savedAccount));
    }
    @PrePersist
    public String generateAccountNumber() {
        Random random = new Random();
        int accountNumber = random.nextInt(90000000) + 10000000;
        return String.valueOf(accountNumber);
    }

    public AccountDto getAccount(Long accountId) {
        return accountMapper.toDto(accountRepository.findById(accountId).get());
    }

    public AccountDto getAccountByAccountNumber(String accountNumber) {
        return accountMapper.toDto(accountRepository.findByAccountNumber(accountNumber));
    }

    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream().map(accountMapper::toDto).collect(Collectors.toList());
    }

    public AccountDto updateAccount(Long accountId, AccountDto accountDto) {
        AccountEntity existingAccount = accountRepository.findById(accountId).get();
        AccountEntity updatedAccount = accountMapper.partialUpdate(accountDto, existingAccount);
        AccountEntity savedAccount = accountRepository.save(updatedAccount);
        return accountMapper.toDto(savedAccount);
    }

    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }

}
