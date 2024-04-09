package com.banktalib.paymentservice.PaymentService.Controller;

import com.banktalib.paymentservice.PaymentService.Dto.AmountRequestDto;
import com.banktalib.paymentservice.PaymentService.Dto.TransferDto;
import com.banktalib.paymentservice.PaymentService.Service.IDepositService;
import com.banktalib.paymentservice.PaymentService.Service.ITransactionService;
import com.banktalib.paymentservice.PaymentService.Service.ITransferService;
import com.banktalib.paymentservice.PaymentService.Service.IWithdrawalService;
import com.banktalib.paymentservice.PaymentService.Service.Implimenetation.DepositService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/transactions")
public class PaymentController {

    @Autowired
    private IDepositService depositService;

    @Autowired
    private IWithdrawalService withdrawalService;

    @Autowired
    private ITransferService transferService;

    @PostMapping("/deposit/{LoggedAccountNumber}")
    public ResponseEntity<?> cashDeposit(@RequestBody AmountRequestDto amountRequest, @PathVariable("LoggedAccountNumber") String LoggedAccountNumber) {

        if (amountRequest.getAmount() <= 0) {
            Map<String, String> err = new HashMap<>();
            err.put("Error", "Invalid amount");
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }

        depositService.cashDeposit(LoggedAccountNumber, amountRequest.getAmount());

        Map<String, String> response = new HashMap<>();
        response.put("msg", "Cash deposited successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/withdraw/{LoggedAccountNumber}")
    public ResponseEntity<?> cashWithdrawal(@RequestBody AmountRequestDto amountRequest, @PathVariable("LoggedAccountNumber") String LoggedAccountNumber) {
        if (amountRequest.getAmount() <= 0) {
            Map<String, String> err = new HashMap<>();
            err.put("Error", "Invalid amount");
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
        withdrawalService.cashWithdrawal(LoggedAccountNumber,
                amountRequest.getAmount());

        Map<String, String> response = new HashMap<>();
        response.put("msg", "Cash withdrawn successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/transfer/{LoggedAccountNumber}")
    public ResponseEntity<?> fundTransfer(@RequestBody TransferDto TransferRequest, @PathVariable("LoggedAccountNumber") String LoggedAccountNumber) {
        if (TransferRequest.getAmount() <= 0) {
            Map<String, String> err = new HashMap<>();
            err.put("Error", "Invalid amount");
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }

        transferService.fundTransfer(LoggedAccountNumber, TransferRequest.getTargetAccountNumber()
                , TransferRequest.getAmount());
        Map<String, String> response = new HashMap<>();
        response.put("msg", "Fund transferred successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
