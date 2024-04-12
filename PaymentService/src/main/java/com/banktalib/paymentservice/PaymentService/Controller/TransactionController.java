package com.banktalib.paymentservice.PaymentService.Controller;

import com.banktalib.paymentservice.PaymentService.Dto.TransactionDto;
import com.banktalib.paymentservice.PaymentService.Service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;


    @PostMapping("/createTransaction")
    public ResponseEntity<TransactionDto> saveTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionDto savedTransaction = transactionService.saveTransaction(transactionDto);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/{LoggedAccountNumber}")
    public ResponseEntity<List<TransactionDto>> getAllTransactionsByAccountNumber(@PathVariable("LoggedAccountNumber") String LoggedAccountNumber) {
        return new ResponseEntity<>(transactionService
                .getAllTransactionsByAccountNumber(LoggedAccountNumber), HttpStatus.OK);
    }

    @GetMapping("/getTransactionById/{id}")
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.getTransaction(id), HttpStatus.OK);
    }


    @PutMapping("/updateTransaction/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        return new ResponseEntity<>(transactionService.updateTransaction(id, transactionDto), HttpStatus.OK);
    }

    @DeleteMapping("/deleteTransaction/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
