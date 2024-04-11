package com.banktalib.billsmicroservice.BillService.Service;


import com.banktalib.billsmicroservice.BillService.Dto.BillDto;

import java.util.List;

public interface IBillService {


    BillDto createBill(BillDto billDto);

    BillDto getBillByAccountNumberInitiated(String accountNumberInitiated);

    BillDto getBillByAccountNumberInvolved(String accountNumberInvolved);

    BillDto updateBill(BillDto billDto);
}