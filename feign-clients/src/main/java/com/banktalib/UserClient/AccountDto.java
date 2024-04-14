package com.banktalib.UserClient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class AccountDto implements Serializable {
    private Long idAccount;
    private String accountNumber;
    private Double balance;

}