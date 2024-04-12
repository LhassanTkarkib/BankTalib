package com.banktalib.billsmicroservice.BillService.Repository;

import com.banktalib.billsmicroservice.BillService.Entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {


    List<BillEntity> findAllByAccountNumberInitiated(String accountNumberInitiated);
//    BillEntity findAllByAccountNumberInitiated(String accountNumberInitiated);
    BillEntity findByIdBill(Long idBill);

    List<BillEntity> findAllByPayersAccountNumber(String accountNumberInvolved);
}
