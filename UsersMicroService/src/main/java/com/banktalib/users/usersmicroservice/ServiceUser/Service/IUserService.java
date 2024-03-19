package com.banktalib.users.usersmicroservice.ServiceUser.Service;

import com.banktalib.users.usersmicroservice.ServiceUser.Dto.UserDto;

import java.util.List;

public interface IUserService {
    UserDto createUser(UserDto userDTO);

    UserDto getUser(Long id);

    UserDto updateUser(long id,UserDto userDTO);

    void deleteUser(Long id);

    List<UserDto> getAllUsers();

//    List<UserDto> getAllUsersByRole(RoleUser role);
}
