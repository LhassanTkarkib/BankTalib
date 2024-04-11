package com.banktalib.billsmicroservice.BillService.Service.Implimenetation;

import com.banktalib.billsmicroservice.BillService.Dto.BillDto;
import com.banktalib.billsmicroservice.BillService.Entity.BillEntity;
import com.banktalib.billsmicroservice.BillService.Enums.PayementStatus;
import com.banktalib.billsmicroservice.BillService.Mapper.BillMapper;
import com.banktalib.billsmicroservice.BillService.Repository.BillRepository;
import com.banktalib.billsmicroservice.BillService.Service.IBillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService implements IBillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillMapper billMapper;

    @Override
    public BillDto createBill(BillDto billDto) {
//        billDto.setPayementStatus(PayementStatus.UNPAID);
        BillEntity savedBill = billRepository.save(billMapper.toEntity(billDto));
        return billMapper.toDto(savedBill);
    }

    @Override
    public BillDto getBillByAccountNumberInitiated(String accountNumberInitiated) {
        BillEntity bill = billRepository.findAllByAccountNumberInitiated(accountNumberInitiated);
        return billMapper.toDto(bill);
    }

    @Override
    public BillDto getBillByAccountNumberInvolved(String PayersAccountNumber) {
        BillEntity bill = billRepository.findAllByPayersAccountNumber(PayersAccountNumber);
        return billMapper.toDto(bill);
    }

    @Override
    public BillDto updateBill(BillDto billDto) {
        BillEntity bill = billRepository.findAllByAccountNumberInitiated(billDto.getAccountNumberInitiated());
        bill = billMapper.partialUpdate(billDto, bill);
        bill = billRepository.save(bill);
        return billMapper.toDto(bill);
    }




}
