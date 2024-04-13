package com.banktalib.users.usersmicroservice.ServiceUser.Controller;
import com.banktalib.users.usersmicroservice.ServiceUser.Controller.UserController;
import com.banktalib.users.usersmicroservice.ServiceUser.Dto.UserDto;
import com.banktalib.users.usersmicroservice.ServiceUser.Service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    private UserDto userDto;

    @BeforeEach
    public void setUp() {
        userDto = new UserDto();
        userDto.setUsername("testUser");
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<UserDto> userDtoList = Collections.singletonList(userDto);
        when(userService.getAllUsers()).thenReturn(userDtoList);

        mockMvc.perform(get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idUser").value(userDto.getIdUser()))
                .andExpect(jsonPath("$[0].username").value(userDto.getUsername()));
    }

    @Test
    public void testGetUserById() throws Exception {
        when(userService.getUser(anyLong())).thenReturn(userDto);

        mockMvc.perform(get("/api/v1/users/getUserById/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUser").value(userDto.getIdUser()))
                .andExpect(jsonPath("$.username").value(userDto.getUsername()));
    }

    @Test
    public void testCreateUser() throws Exception {
        when(userService.createUser(any())).thenReturn(userDto);

        mockMvc.perform(post("/api/v1/users/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idUser\":1,\"username\":\"testUser\",\"firstname\":\"test\",\"lastname\":\"test\",\"email\":\"test@gmail.com\",\"password\":\"testPass\",\"gender\":\"MALE\",\"role\":\"STUDENT\",\"account\":null}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idUser").value(userDto.getIdUser()))
                .andExpect(jsonPath("$.username").value(userDto.getUsername()));
    }

    @Test
    public void testUpdateUser() throws Exception {
        when(userService.updateUser(anyLong(), any())).thenReturn(userDto);

        mockMvc.perform(put("/api/v1/users/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idUser\":1,\"username\":\"testUser\",\"firstname\":\"test\",\"lastname\":\"test\",\"email\":\"test@gmail.com\",\"password\":\"testPass\",\"gender\":\"MALE\",\"role\":\"STUDENT\",\"account\":null}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUser").value(userDto.getIdUser()))
                .andExpect(jsonPath("$.username").value(userDto.getUsername()));
    }

    @Test
    public void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(anyLong());

        mockMvc.perform(delete("/api/v1/users/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}