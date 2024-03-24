package com.banktalib.paymentservice.PaymentService.Repository;

import com.banktalib.paymentservice.PaymentService.Entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
