package com.banktalib.users.usersmicroservice.ServiceUser.Service.Implimentation;

import com.banktalib.users.usersmicroservice.ServiceUser.Dto.UserDto;
import com.banktalib.users.usersmicroservice.ServiceUser.Entity.UserEntity;
import com.banktalib.users.usersmicroservice.ServiceUser.Mapper.UserMapper;
import com.banktalib.users.usersmicroservice.ServiceUser.Repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private UserDto userDto;
    private UserEntity userEntity;

    @BeforeEach
    public void setUp() {
        userDto = new UserDto();
        userDto.setUsername("testUser");

        userEntity = new UserEntity();
        userEntity.setUsername("testUser");
    }

    @Test
    public void testCreateUser() {
        when(userMapper.userDtoToUserEntity(userDto)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.userEntityToUserDto(userEntity)).thenReturn(userDto);

        UserDto result = userService.createUser(userDto);

        assertEquals(userDto, result);
    }

    @Test
    public void testGetUserByUserName() {
        when(userRepository.findUserByUsername("testUser")).thenReturn(userEntity);
        when(userMapper.userEntityToUserDto(userEntity)).thenReturn(userDto);

        UserDto result = userService.getUserByUserName("testUser");

        assertEquals(userDto, result);
    }

    @Test
    public void testUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(userMapper.partialUpdate(userDto, userEntity)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.userEntityToUserDto(userEntity)).thenReturn(userDto);

        UserDto result = userService.updateUser(1L, userDto);

        assertEquals(userDto, result);
    }

    @Test
    public void testGetAllUsers() {
        List<UserEntity> users = Collections.singletonList(userEntity);

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.userEntityToUserDto(userEntity)).thenReturn(userDto);

        List<UserDto> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals(userDto, result.get(0));
    }

    @AfterEach
    public void tearDown() {
        userDto = null;
        userEntity = null;
    }
}