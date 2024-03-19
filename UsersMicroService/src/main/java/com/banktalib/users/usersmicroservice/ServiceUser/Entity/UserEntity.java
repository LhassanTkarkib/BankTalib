package com.banktalib.users.usersmicroservice.ServiceUser.Entity;

import com.banktalib.users.usersmicroservice.ServiceUser.Enums.Gender;
import com.banktalib.users.usersmicroservice.ServiceUser.Enums.RoleUser;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "\"user\"")
public class UserEntity {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence")
    private Long idUser;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleUser role;

    @Column(name = "idSold")
    private long idSold;

    @Column(name = "idBills")
    private long idBills;

    @Column(name = "idEvents")
    private long idEvents;

    @Column(name = "idEventsPayement")
    private long idEventsPayement;

    @Column(name = "idTransaction")
    private long idTransaction;

    @Column(name = "idItemsForSale")
    private long idItemsForSale;

    @Column(name = "idItemsBought")
    private long idItemsBought;

    @Column(name = "idNotifications")
    private long idNotifications;

}
