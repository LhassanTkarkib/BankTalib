package com.banktalib.billsmicroservice.BillService.Service.Implimenetation;

import com.banktalib.UserClient.*;
import com.banktalib.billsmicroservice.BillService.Dto.BillDto;
import com.banktalib.billsmicroservice.BillService.Entity.BillEntity;
import com.banktalib.billsmicroservice.BillService.Enums.PayementStatus;
import com.banktalib.billsmicroservice.BillService.Mapper.BillMapper;
import com.banktalib.billsmicroservice.BillService.Repository.BillRepository;
import com.banktalib.billsmicroservice.BillService.Service.IBillService;

import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService implements IBillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillMapper billMapper;
    @Autowired
    private AccountClient accountClient;
    @Autowired
    private TransactionsClient transactionsClient;

    @Override
    public BillDto createBill(BillDto billDto) {

        BillEntity savedBill = billRepository.save(billMapper.toEntity(billDto));
        return billMapper.toDto(savedBill);
    }

    @Override
    public List<BillDto> getAllBillsByAccountNumberInitiated(String accountNumberInitiated) {
        List<BillEntity> billEntities = billRepository.findAllByAccountNumberInitiated(accountNumberInitiated);
        return billEntities.stream().map(billMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<BillDto> getAllBillsByAccountNumberInvolved(String PayersAccountNumber) {
        List<BillEntity> billEntities = billRepository.findAllByPayersAccountNumber(PayersAccountNumber);
        return billEntities.stream()
                .filter(bill -> bill.getPayementStatus() == PayementStatus.UNPAID)
                .map(billMapper::toDto)
                .collect(Collectors.toList());
    }

    // update using id
    @Override
    @Transactional
    public BillDto payBill(Long id, BillDto billDto,String accountNumberInvolved) {

        double amount = billDto.getAmount();
        AccountDto account = accountClient.getAccountByAccountNumber(accountNumberInvolved);
        if (account == null) {
            throw new NotFoundException("Account not found");
        }

        AccountDto billOwner = accountClient.getAccountByAccountNumber(billDto.getAccountNumberInitiated());
        if (billOwner == null) {
            throw new NotFoundException("billOwner not found");
        }

        double currentBalance = account.getBalance();
        if (currentBalance < amount) {
            throw new NotFoundException("Insufficient balance");
        }
        double newBalance = currentBalance - amount;
        account.setBalance(newBalance);
        accountClient.updateAccount(account.getIdAccount() , account);

        double billOwnerBalance = billOwner.getBalance()+amount;
        billOwner.setBalance(billOwnerBalance);
        accountClient.updateAccount(billOwner.getIdAccount(),billOwner);

        BillEntity bill = billRepository.findByIdBill(id);
        if (bill == null) {
            throw new NotFoundException("bill not found");
        }

        bill.setPayementStatus(PayementStatus.PAID);
        bill = billRepository.save(bill);

        TransactionDto transaction = new TransactionDto();
        transaction.setAmount(amount);
        transaction.setTypeTransaction(TransactionType.BILL_PAYMENT);
        transaction.setDateTransaction(new Date());
        transaction.setSenderAccountNumber(account.getAccountNumber());
        transaction.setReceiverAccountNumber(billOwner.getAccountNumber());
        transactionsClient.createTransaction(transaction);

            return billMapper.toDto(bill);
        }
    }



