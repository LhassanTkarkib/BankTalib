package com.banktalib.users.usersmicroservice.ServiceUser.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")
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
    private String accountNumber;


    @Column(name = "Balance")
    private double balance;

    @OneToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private UserEntity user;



//
//    @Column(name = "idBills")
//    private long idBills;
//
//    @Column(name = "idEvents")
//    private long idEvents;
//
//    @Column(name = "idEventsPayement")
//    private long idEventsPayement;
//
//    @Column(name = "idTransaction")
//    private long idTransaction;
//
//    @Column(name = "idItemsForSale")
//    private long idItemsForSale;
//
//    @Column(name = "idItemsBought")
//    private long idItemsBought;
//
//    @Column(name = "idNotifications")
//    private long idNotifications;

}
