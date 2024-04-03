package com.banktalib.users.usersmicroservice.ServiceUser.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Account")
public class AccountEntity {

    @Id
    @SequenceGenerator(
            name = "acount_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence")
    private Long idAccount;

    @Column(name = "accountNumber")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String accountNumber;


    @Column(name = "Balance")
    private double balance;

//    private Long idUser;

    @OneToOne(mappedBy = "account")
    private UserEntity user;


}
