package com.banktalib.users.usersmicroservice.ServiceUser.Dto;

import com.banktalib.users.usersmicroservice.ServiceUser.Entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Long idAccount;
    private String accountNumber;
    private Double balance;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idUser;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserEntity user;
}