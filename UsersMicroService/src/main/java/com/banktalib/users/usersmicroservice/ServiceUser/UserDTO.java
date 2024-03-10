package com.banktalib.users.usersmicroservice.ServiceUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotNull
    private Long idUser;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_]*$")
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private Gender gender;

    @NotNull
    private RoleUser role;

    private long idSold;

    private long idBills;

    private long idEvents;

    private long idEventsPayement;
}
