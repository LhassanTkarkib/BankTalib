package com.banktalib.users.usersmicroservice.ServiceUser.Service.Implimentation;

import com.banktalib.users.usersmicroservice.ServiceUser.Entity.UserEntity;
import com.banktalib.users.usersmicroservice.ServiceUser.Service.IUserService;
import com.banktalib.users.usersmicroservice.ServiceUser.Dto.UserDto;
import com.banktalib.users.usersmicroservice.ServiceUser.Mapper.UserMapper;
import com.banktalib.users.usersmicroservice.ServiceUser.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        UserEntity userEntity = userMapper.userDtoToUserEntity(userDto);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return userMapper.userEntityToUserDto(savedUserEntity);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntity = userRepository.findAll();
        return userEntity.stream().map(userMapper::userEntityToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(Long userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if(userEntityOptional.isPresent()){
            return userMapper.userEntityToUserDto(userEntityOptional.get());
        }
        return null;
    }
    @Override
    public UserDto updateUser(long id, UserDto userDTO) {
        UserEntity existingUser = userRepository.findById(id).get();
        UserEntity updatedUser = userMapper.partialUpdate(userDTO, existingUser);
        UserEntity savedUserEntity = userRepository.save(updatedUser);
        return userMapper.userEntityToUserDto(savedUserEntity);
    }
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }



}