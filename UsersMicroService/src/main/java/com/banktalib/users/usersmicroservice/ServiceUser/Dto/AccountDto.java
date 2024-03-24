package com.banktalib.users.usersmicroservice.ServiceUser.Dto;

import com.banktalib.users.usersmicroservice.ServiceUser.Entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link com.banktalib.users.usersmicroservice.ServiceUser.Entity.AccountEntity}
 */
@AllArgsConstructor
@Data
public class AccountDto implements Serializable {
    private final Long idAccount;
    private final String accountNumber;
    private final double balance;
    private final UserEntity user;
}