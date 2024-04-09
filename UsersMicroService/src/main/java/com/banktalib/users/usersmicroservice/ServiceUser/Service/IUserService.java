package com.banktalib.users.usersmicroservice.ServiceUser.Service;

import com.banktalib.users.usersmicroservice.ServiceUser.Dto.UserDto;

import java.util.List;

public interface IUserService {
    UserDto createUser(UserDto userDTO);

    UserDto getUser(Long id);


    UserDto updateUser(long id, UserDto userDTO);

    UserDto updateUser(String username, UserDto userDto);

    void deleteUser(Long id);

    List<UserDto> getAllUsers();

    UserDto getUserByUserName(String userName);

//    List<UserDto> getAllUsersByRole(RoleUser role);
}
