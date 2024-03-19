package com.banktalib.users.usersmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class UsersMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersMicroServiceApplication.class, args);
    }

}
