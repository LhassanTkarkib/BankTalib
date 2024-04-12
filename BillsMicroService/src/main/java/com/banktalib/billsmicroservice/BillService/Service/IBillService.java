package com.banktalib.billsmicroservice.BillService.Service;


import com.banktalib.billsmicroservice.BillService.Dto.BillDto;

import java.util.List;

public interface IBillService {


    BillDto createBill(BillDto billDto);

    List<BillDto> getAllBillsByAccountNumberInitiated(String accountNumberInitiated);

    List<BillDto> getAllBillsByAccountNumberInvolved(String accountNumberInvolved);

//    BillDto updateBill(Long id,BillDto billDto);

    // update using id
    BillDto payBill(Long id, BillDto billDto,String accountNumberInvolved);
}