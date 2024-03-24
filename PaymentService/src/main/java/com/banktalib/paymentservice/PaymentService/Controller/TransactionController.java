package com.banktalib.paymentservice.PaymentService.Controller;

import com.banktalib.paymentservice.PaymentService.Dto.TransactionDto;
import com.banktalib.paymentservice.PaymentService.Service.ITransactionService;
import com.banktalib.users.usersmicroservice.ServiceUser.Dto.UserDto;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;



    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @GetMapping ("/getTransactionById/{id}")
    public ResponseEntity<TransactionDto> getTransaction(Long id) {
        return new ResponseEntity<>(transactionService.getTransaction(id), HttpStatus.OK);
    }

    @GetMapping ("/createTransaction")
    public ResponseEntity<TransactionDto> createTransaction(TransactionDto transactionDto) {
        return new ResponseEntity<>(transactionService.createTransaction(transactionDto), HttpStatus.CREATED);
    }

    @GetMapping ("/updateTransaction/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(Long id, TransactionDto transactionDto) {
        return new ResponseEntity<>(transactionService.updateTransaction(id, transactionDto), HttpStatus.OK);
    }

    @GetMapping ("/deleteTransaction/{id}")
    public ResponseEntity<Void> deleteTransaction(Long id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
