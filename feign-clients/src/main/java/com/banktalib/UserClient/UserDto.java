package com.banktalib.UserClient;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto implements Serializable {

    @JsonIgnore
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