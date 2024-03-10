package com.banktalib.ServiceUser;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
@Data
@Builder
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

    @NotNull
    private Gender gender;

    @NotNull
    private RoleUser role;

    private long idSold;

    private long idBills;

    private long idEvents;

    private long idEventsPayement;
}
