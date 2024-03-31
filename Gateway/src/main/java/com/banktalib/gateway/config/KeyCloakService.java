package com.banktalib.gateway.config;

import com.banktalib.gateway.config.DTO.AuthenticationResponse;
import com.banktalib.gateway.config.DTO.UserAuthenticationDto;
import com.banktalib.gateway.config.DTO.UserDto;
import com.banktalib.gateway.config.DTO.UserRepresentationDto;
import com.banktalib.gateway.config.Mapper.UserRepresentationMapper;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class KeyCloakService {

    private final UserRepresentationMapper userRepresentationMapper;

    public KeyCloakService(UserRepresentationMapper userRepresentationMapper) {
        this.userRepresentationMapper = userRepresentationMapper;
    }

    public UserRepresentationDto addUser(UserDto userDTO){
        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmailId());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);

        Keycloak instance = KeycloakConfig.getInstance();
        UsersResource usersResource = instance.realm(KeycloakConfig.realm).users();
        usersResource.create(user);

    return userRepresentationMapper.toDto(user);
    }

    public AuthenticationResponse authenticate(UserAuthenticationDto userDTO){
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(KeycloakConfig.serverUrl)
                .realm(KeycloakConfig.realm)
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(KeycloakConfig.clientId)
                .clientSecret(KeycloakConfig.clientSecret)
                .username(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();


        return AuthenticationResponse.builder()
                .accessToken(keycloak.tokenManager().getAccessToken())
                .refreshToken(keycloak.tokenManager().refreshToken())
                .build();
    }


    public List<UserRepresentation> getUser(String userName){
        Keycloak instance = KeycloakConfig.getInstance();
        UsersResource usersResource = instance.realm(KeycloakConfig.realm).users();
        return usersResource.search(userName);
    }

    public void updateUser(String userId, UserDto userDTO){
        UserRepresentation user = new UserRepresentation();
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmailId());
        user.setEnabled(true);

        Keycloak instance = KeycloakConfig.getInstance();
        UsersResource usersResource = instance.realm(KeycloakConfig.realm).users();
        usersResource.get(userId).update(user);
    }

    public void deleteUser(String userId){
        Keycloak instance = KeycloakConfig.getInstance();
        UsersResource usersResource = instance.realm(KeycloakConfig.realm).users();
        usersResource.get(userId).remove();
    }
}