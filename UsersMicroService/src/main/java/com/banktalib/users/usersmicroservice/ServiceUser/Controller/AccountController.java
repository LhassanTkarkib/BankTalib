package com.banktalib.users.usersmicroservice.ServiceUser.Controller;

import com.banktalib.users.usersmicroservice.ServiceUser.Dto.AccountDto;
import com.banktalib.users.usersmicroservice.ServiceUser.Service.IAccountService;
import com.banktalib.users.usersmicroservice.ServiceUser.Service.Implimentation.AccountService;
import org.mapstruct.ap.shaded.freemarker.core.ReturnInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    public IAccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        return new ResponseEntity<>( accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long accountId){
        return new ResponseEntity<>(accountService.getAccount(accountId),HttpStatus.OK);
    }

    @PostMapping("/createAccount")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
    }

    @PutMapping("/updateAccount/{accountId}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long accountId, @RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.updateAccount(accountId, accountDto),HttpStatus.OK);
    }

    @DeleteMapping("/deleteAccount/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId){
        accountService.deleteAccount(accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
