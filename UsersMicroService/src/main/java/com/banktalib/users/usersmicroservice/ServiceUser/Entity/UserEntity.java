package com.banktalib.users.usersmicroservice.ServiceUser.Entity;

import com.banktalib.users.usersmicroservice.ServiceUser.Enums.Gender;
import com.banktalib.users.usersmicroservice.ServiceUser.Enums.RoleUser;
import jakarta.persistence.*;
import lombok.*;


@Data
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

    @Column(name = "username")
    private String username;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

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

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "idAccount", referencedColumnName = "idAccount")
    private AccountEntity account;

}
