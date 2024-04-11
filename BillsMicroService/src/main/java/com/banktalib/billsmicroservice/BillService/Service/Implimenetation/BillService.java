package com.banktalib.billsmicroservice.BillService.Service.Implimenetation;

import com.banktalib.billsmicroservice.BillService.Dto.BillDto;
import com.banktalib.billsmicroservice.BillService.Entity.BillEntity;
import com.banktalib.billsmicroservice.BillService.Enums.PayementStatus;
import com.banktalib.billsmicroservice.BillService.Mapper.BillMapper;
import com.banktalib.billsmicroservice.BillService.Repository.BillRepository;
import com.banktalib.billsmicroservice.BillService.Service.IBillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService implements IBillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillMapper billMapper;

    @Override
    public BillDto createBill(BillDto billDto) {
        BillEntity savedBill = billRepository.save(billMapper.toEntity(billDto));
        return billMapper.toDto(savedBill);
    }

    @Override
    public List<BillDto> getBillByAccountNumberInitiated(String accountNumberInitiated) {
        List<BillEntity> billEntities = billRepository.findAllByAccountNumberInitiated(accountNumberInitiated);
        return billEntities.stream().map(billMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<BillDto> getBillByAccountNumberInvolved(String PayersAccountNumber) {
        List<BillEntity> billEntities = billRepository.findAllByPayersAccountNumber(PayersAccountNumber);
        return billEntities.stream().map(billMapper::toDto).collect(Collectors.toList());
    }

    // update using id
    @Override
    public BillDto updateBill(Long id,BillDto billDto) {

        BillEntity bill = billRepository.findByIdBill(id);
        bill = billMapper.partialUpdate(billDto, bill);
        bill = billRepository.save(bill);
        return billMapper.toDto(bill);
    }




}
