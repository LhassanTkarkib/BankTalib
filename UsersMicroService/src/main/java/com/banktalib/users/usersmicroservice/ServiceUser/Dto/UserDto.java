package com.banktalib.users.usersmicroservice.ServiceUser.Dto;

import com.banktalib.users.usersmicroservice.ServiceUser.Entity.AccountEntity;
import com.banktalib.users.usersmicroservice.ServiceUser.Enums.Gender;
import com.banktalib.users.usersmicroservice.ServiceUser.Enums.RoleUser;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto implements Serializable {
        private Long idUser;
        private String username;
        private String firstname;
        private String lastname;
        private String email;
        private String password;
        private Gender gender;
        private RoleUser role;
        private AccountDto account;
}