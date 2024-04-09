package com.banktalib.UserClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "userClient", url = "http://localhost:5050/api/v1/users")
public interface UserClient {

    @PostMapping("/createUser")
    UserDto createUser(@RequestBody UserDto userDto);

    @GetMapping("/getUserById/{userId}")
    UserDto getUserById(@PathVariable Long userId);

    @GetMapping("/getUserByUserName/{userName}")
    UserDto getUserByUserName(@PathVariable String userName);

    @GetMapping
    List<UserDto> getAllUsers();

    @PutMapping("/update/{userId}")
    UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDTO);

    @PutMapping("/update/byUserName/{userName}")
    UserDto updateUserByUserName(@PathVariable String userName, @RequestBody UserDto userDTO);

    @DeleteMapping("/delete/{userId}")
    void deleteUser(@PathVariable Long userId);
}