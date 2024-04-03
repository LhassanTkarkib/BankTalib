package com.banktalib.UserClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "userClient", url = "http://localhost:5050")
public interface UserClient {

    @PostMapping(value = "/api/v1/users/createUser")
    UserDto createUser(@RequestBody UserDto user);

//    @GetMapping("/api/v1/users")
//    ResponseEntity<List<UserDto>> getAllUsers();

//    @GetMapping("/api/v1/users/getUserById/{userId}")
//    ResponseEntity<UserDto> getUserById(@PathVariable Long id);
//
//    @PutMapping("/api/v1/users/update/{userId}")
//    ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody @Valid UserDto user);
//
//    @DeleteMapping("/api/v1/users/delete/{userId}")
//    ResponseEntity<Void> deleteUser(@PathVariable Long id);
}
