package com.banktalib.gateway.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

public class KeycloakConfig {

    static Keycloak keycloak = null;
//    final static String serverUrl = "http://localhost:7080";
////    final static String serverUrl = "http://localhost:7080/auth";
//    public final static String realm = "BankTalib";
//    final static String clientId = "Bank-Talib-auth";
//    final static String clientSecret = "mEYppBHFLlP0v8YL31tvLdmASplfN23k";
//    final static String userName = "ryu ryu";
//    final static String password = "123456";

    final static String serverUrl = "http://localhost:7080";
    //    final static String serverUrl = "http://localhost:7080/auth";
    public final static String realm = "master";
    final static String clientId = "social-network-auth";
    final static String clientSecret = "xPhSVadmKcsDcQYewdUhvayNo4xTWKP4";
    final static String userName = "admin";
    final static String password = "admin";

    public KeycloakConfig() {
    }

    public static Keycloak getInstance() {
        if (keycloak == null) {

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .resteasyClient(new ResteasyClientBuilder()
                            .connectionPoolSize(10)
                            .build())
                    .build();
        }
        return keycloak;
    }
}