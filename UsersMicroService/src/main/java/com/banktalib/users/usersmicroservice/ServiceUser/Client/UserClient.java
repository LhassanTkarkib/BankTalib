package com.banktalib.users.usersmicroservice.ServiceUser.Client;

import com.banktalib.users.usersmicroservice.ServiceUser.Dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("USER")
public interface UserClient {

    @PostMapping("/api/v1/users/createUser")
    ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user);

    @GetMapping("/api/v1/users")
    ResponseEntity<List<UserDto>> getAllUsers();

    @GetMapping("/api/v1/users/getUserById/{userId}")
    ResponseEntity<UserDto> getUserById(@PathVariable Long id);

    @PutMapping("/api/v1/users/update/{userId}")
    ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody @Valid UserDto user);

    @DeleteMapping("/api/v1/users/delete/{userId}")
    ResponseEntity<Void> deleteUser(@PathVariable Long id);
}
