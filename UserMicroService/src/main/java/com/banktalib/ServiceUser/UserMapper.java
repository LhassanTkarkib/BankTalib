package com.banktalib.ServiceUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface UserMapper {

    com.banktalib.ServiceUser.UserMapper INSTANCE = Mappers.getMapper(com.banktalib.ServiceUser.UserMapper.class);

    User userDtoToUserEntity(UserDTO userDto);
    UserDTO userEntityToUserDto(User userEntity);
}