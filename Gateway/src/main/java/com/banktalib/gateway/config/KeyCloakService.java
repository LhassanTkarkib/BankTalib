package com.banktalib.gateway.config;

import com.banktalib.UserClient.UserClient;
import com.banktalib.UserClient.UserDto;
import com.banktalib.gateway.config.DTO.AuthenticationResponse;
import com.banktalib.gateway.config.DTO.UserAuthenticationDto;

import com.banktalib.gateway.config.Exeptions.UserAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class KeyCloakService {

    private UserClient userClient;

    public RealmResource getKeycloak() {
        Keycloak instance = KeycloakConfig.getInstance();
        return instance.realm(KeycloakConfig.realm);
    }

    public UserDto addUser(UserDto userDTO) {
        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);

        UsersResource usersResource = getKeycloak().users();
        List<UserRepresentation> userRepresentations = usersResource.search("");

        boolean userExists = userRepresentations.stream().anyMatch(u ->
                (u.getUsername() != null && u.getUsername().equals(userDTO.getUsername())) ||
                        (u.getEmail() != null && u.getEmail().equals(userDTO.getEmail()))
        );

        if (userExists) {
            throw new UserAlreadyExistsException("User already exists");
        } else {
            usersResource.create(user);
            return userClient.createUser(userDTO);
        }
    }

    public AuthenticationResponse authenticate(UserAuthenticationDto userDTO) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(KeycloakConfig.serverUrl)
                .realm(KeycloakConfig.realm)
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(KeycloakConfig.clientId)
                .clientSecret(KeycloakConfig.clientSecret)
                .username(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();

        AccessTokenResponse accesstoken = keycloak.tokenManager().getAccessToken();
        AccessTokenResponse refreshToken = keycloak.tokenManager().refreshToken();


        return AuthenticationResponse.builder()
                .accessToken(accesstoken)
                .refreshToken(refreshToken)
                .build();

    }

    public List<UserRepresentation> getUser(String userName) {
        Keycloak instance = KeycloakConfig.getInstance();
        UsersResource usersResource = instance.realm(KeycloakConfig.realm).users();
        return usersResource.search(userName);
    }
    public UserDto updateUser(String username, UserDto userDTO) {
        try {

        UserRepresentation user = new UserRepresentation();
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setEnabled(true);

        CredentialRepresentation credentials = new CredentialRepresentation();
        credentials.setType(CredentialRepresentation.PASSWORD);
        credentials.setValue(userDTO.getPassword());
        credentials.setTemporary(false);
        user.setCredentials(Collections.singletonList(credentials));

        Keycloak instance = KeycloakConfig.getInstance();
        UsersResource usersResource = instance.realm(KeycloakConfig.realm).users();
        String userId = usersResource.search(username).get(0).getId();
        usersResource.get(userId).update(user);

            return userClient.updateUserByUserName(username, userDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void deleteUser(String userId) {
        Keycloak instance = KeycloakConfig.getInstance();
        UsersResource usersResource = instance.realm(KeycloakConfig.realm).users();
        usersResource.get(userId).remove();
    }
}