package com.banktalib.users.usersmicroservice.ServiceUser.Repository;

import com.banktalib.users.usersmicroservice.ServiceUser.Entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Boolean existsByAccountNumber(String accountNumber);

    AccountEntity findByAccountNumber(String accountNumber);
}
