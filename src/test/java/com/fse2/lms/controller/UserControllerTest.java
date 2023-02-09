package com.fse2.lms.controller;

import com.fse2.lms.dto.request.LoginUserRequestDto;
import com.fse2.lms.dto.request.UserRequestDto;
import com.fse2.lms.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userService;

    @Test
    void userAddedWhenProvidedValidUserDetails() {
        Mockito.doNothing().when(userService).addUser(any(UserRequestDto.class));
        ResponseEntity<String> response = userController.addUser(any(UserRequestDto.class));
        assertEquals("User added", response.getBody());
    }

    @Test
    void userValidatedWhenProvidedValidUserDetails() {
        Mockito.doNothing().when(userService).validateUser(any(LoginUserRequestDto.class));
        ResponseEntity<String> response = userController.validateUser(any(LoginUserRequestDto.class));
        assertEquals("User authenticated", response.getBody());
    }


}
