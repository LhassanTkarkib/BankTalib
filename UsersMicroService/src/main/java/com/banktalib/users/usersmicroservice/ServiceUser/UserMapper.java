package com.banktalib.users.usersmicroservice.ServiceUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDtoToUserEntity(UserDTO userDto);
    UserDTO userEntityToUserDto(User userEntity);
}