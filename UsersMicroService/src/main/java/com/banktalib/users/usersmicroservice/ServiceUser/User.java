package com.banktalib.users.usersmicroservice.ServiceUser;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence")
    private Long idUser;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "role", nullable = false)
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
