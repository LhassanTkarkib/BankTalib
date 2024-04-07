package com.banktalib.UserClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "accountClient", url = "http://localhost:5050")
public interface AccountClient {

//    @PostMapping
}
