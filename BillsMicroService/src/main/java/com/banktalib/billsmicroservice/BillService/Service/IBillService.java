package com.banktalib.billsmicroservice.BillService.Service;


import com.banktalib.billsmicroservice.BillService.Dto.BillDto;

import java.util.List;

public interface IBillService {


    BillDto createBill(BillDto billDto);

    List<BillDto> getBillByAccountNumberInitiated(String accountNumberInitiated);

    List<BillDto> getBillByAccountNumberInvolved(String accountNumberInvolved);

    BillDto updateBill(Long id,BillDto billDto);
}