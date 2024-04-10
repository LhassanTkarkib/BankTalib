package com.banktalib.billsmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.banktalib.UserClient")
public class BillsMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillsMicroServiceApplication.class, args);
    }

}
