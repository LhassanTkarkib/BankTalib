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
    private Long idUser;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Gender gender;
    private RoleUser role;
    private long idSold;
    private long idBills;
    private long idEvents;
    private long idEventsPayement;
    private long idTransaction;
    private long idItemsForSale;
    private long idItemsBought;
    private long idNotifications;
}