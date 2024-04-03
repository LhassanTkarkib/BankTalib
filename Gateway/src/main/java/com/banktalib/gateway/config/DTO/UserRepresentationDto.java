package com.banktalib.gateway.config.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link org.keycloak.representations.idm.UserRepresentation}
 */
@AllArgsConstructor
@Getter
public class UserRepresentationDto implements Serializable {
    private final String id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final List<String> realmRoles;
}