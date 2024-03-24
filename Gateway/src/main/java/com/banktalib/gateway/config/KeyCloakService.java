package com.banktalib.gateway.config;

import com.banktalib.users.usersmicroservice.ServiceUser.Dto.UserDto;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class KeyCloakService {
    public void addUser(UserDto userDTO){
        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);

        Keycloak instance = KeycloakConfig.getInstance();
        UsersResource usersResource = instance.realm(KeycloakConfig.realm).users();
        usersResource.create(user);
    }
}