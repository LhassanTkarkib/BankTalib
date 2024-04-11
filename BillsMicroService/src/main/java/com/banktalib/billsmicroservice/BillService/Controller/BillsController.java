package com.banktalib.billsmicroservice.BillService.Controller;


import com.banktalib.billsmicroservice.BillService.Dto.BillDto;
import com.banktalib.billsmicroservice.BillService.Service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/Bills")
public class BillsController {

    @Autowired
    private IBillService billService;

    @PostMapping("/createBill/{accountNumberInitiated}")
    public ResponseEntity<?> createBill(@RequestBody BillDto billDto,@PathVariable String accountNumberInitiated) {
        billDto.setAccountNumberInitiated(accountNumberInitiated);
        BillDto savedBill = billService.createBill(billDto);
        return new ResponseEntity<>(savedBill, HttpStatus.CREATED);
    }

    @GetMapping("/getBillByAccountNumberInitiated/{accountNumberInitiated}")
    public ResponseEntity<?> getBillByAccountNumberInitiated(@PathVariable String accountNumberInitiated) {
        BillDto bill = billService.getBillByAccountNumberInitiated(accountNumberInitiated);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    @GetMapping("/getBillByAccountNumberInvolved/{accountNumberInvolved}")
    public ResponseEntity<?> getBillByAccountNumberInvolved(@PathVariable String accountNumberInvolved) {
        BillDto bill = billService.getBillByAccountNumberInvolved(accountNumberInvolved);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    @PutMapping("/updateBill")
    public ResponseEntity<?> updateBill(@RequestBody BillDto billDto) {
        BillDto updatedBill = billService.updateBill(billDto);
        return new ResponseEntity<>(updatedBill, HttpStatus.OK);
    }


}
