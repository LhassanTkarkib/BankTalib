package com.banktalib.UserClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "transactionsClient", url = "http://localhost:6060/api/v1/transactions")
public interface TransactionsClient {

    @PostMapping("/createTransaction")
    TransactionDto createTransaction(@RequestBody TransactionDto transactionDto);

    @GetMapping("/{LoggedAccountNumber}")
    List<TransactionDto> getAllTransactionsByAccountNumber(@PathVariable("LoggedAccountNumber") String LoggedAccountNumber);

    @GetMapping("/getTransactionById/{id}")
    TransactionDto getTransaction(@PathVariable Long id);

    @PutMapping("/updateTransaction/{id}")
    TransactionDto updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transactionDto);

    @DeleteMapping("/deleteTransaction/{id}")
    void deleteTransaction(@PathVariable Long id);
}