package com.banktalib.users.usersmicroservice.ServiceUser.Mapper;

import com.banktalib.users.usersmicroservice.ServiceUser.Dto.UserDto;
import com.banktalib.users.usersmicroservice.ServiceUser.Entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {


     UserEntity userDtoToUserEntity(UserDto userDto);
     UserDto userEntityToUserDto(UserEntity userEntity);
}
