package com.banktalib.users.usersmicroservice.ServiceUser.Dto;

import com.banktalib.users.usersmicroservice.ServiceUser.Enums.Gender;
import com.banktalib.users.usersmicroservice.ServiceUser.Enums.RoleUser;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto implements Serializable {
    private String userName;
    private String email;
    private String password;
    private String firstname;
    private String lastName;
    private Gender gender;
    private RoleUser roles;

}