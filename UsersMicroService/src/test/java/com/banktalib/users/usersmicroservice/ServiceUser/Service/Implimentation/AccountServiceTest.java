package com.banktalib.users.usersmicroservice.ServiceUser.Service.Implimentation;

import com.banktalib.users.usersmicroservice.ServiceUser.Dto.AccountDto;
import com.banktalib.users.usersmicroservice.ServiceUser.Entity.AccountEntity;
import com.banktalib.users.usersmicroservice.ServiceUser.Mapper.AccountMapper;
import com.banktalib.users.usersmicroservice.ServiceUser.Repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountService accountService;

    private AccountDto accountDto;
    private AccountEntity accountEntity;

    @BeforeEach
    public void setUp() {
        accountDto = new AccountDto();
        accountDto.setAccountNumber("123456");

        accountEntity = new AccountEntity();
        accountEntity.setAccountNumber("123456");
    }

    @Test
    public void testCreateAccount() {
        when(accountMapper.toEntity(accountDto)).thenReturn(accountEntity);
        when(accountRepository.findByAccountNumber(accountEntity.getAccountNumber())).thenReturn(null);
        when(accountRepository.save(accountEntity)).thenReturn(accountEntity);
        when(accountMapper.toDto(accountEntity)).thenReturn(accountDto);

        AccountDto result = accountService.createAccount(accountDto);

        assertEquals(accountDto, result);
    }

    @Test
    public void testGetAccountByAccountNumber() {
        when(accountRepository.findByAccountNumber("123456")).thenReturn(accountEntity);
        when(accountMapper.toDto(accountEntity)).thenReturn(accountDto);

        AccountDto result = accountService.getAccountByAccountNumber("123456");

        assertEquals(accountDto, result);
    }

    @Test
    public void testUpdateAccount() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(accountEntity));
        when(accountMapper.partialUpdate(accountDto, accountEntity)).thenReturn(accountEntity);
        when(accountRepository.save(accountEntity)).thenReturn(accountEntity);
        when(accountMapper.toDto(accountEntity)).thenReturn(accountDto);

        AccountDto result = accountService.updateAccount(1L, accountDto);

        assertEquals(accountDto, result);
    }

    @Test
    public void testGetAllAccounts() {
        List<AccountEntity> accounts = Collections.singletonList(accountEntity);

        when(accountRepository.findAll()).thenReturn(accounts);
        when(accountMapper.toDto(accountEntity)).thenReturn(accountDto);

        List<AccountDto> result = accountService.getAllAccounts();

        assertEquals(1, result.size());
        assertEquals(accountDto, result.get(0));
    }


}