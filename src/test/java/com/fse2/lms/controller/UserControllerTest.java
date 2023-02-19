package com.fse2.lms.controller;

import com.fse2.lms.dto.request.LoginUserRequestDto;
import com.fse2.lms.dto.request.UserRequestDto;
import com.fse2.lms.dto.response.LoginUserResponseDto;
import com.fse2.lms.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userService;

    private LoginUserRequestDto loginUserRequestDto = LoginUserRequestDto.buildLoginUserRequestDtoWith()
            .userEmailId("akhil.nuthakki1@gmail.com")
            .password("Pass2022")
            .build();

    @Test
    void userAddedWhenProvidedValidUserDetails() {
        Mockito.doNothing().when(userService).addUser(any(UserRequestDto.class));
        ResponseEntity<String> response = userController.addUser(any(UserRequestDto.class));
        assertEquals("User added", response.getBody());
    }

    @Test
    void userValidatedWhenProvidedValidUserDetails() {
        when(userService.validateUser(loginUserRequestDto)).thenReturn(any(LoginUserResponseDto.class));
        ResponseEntity<LoginUserResponseDto> response = userController.validateUser(loginUserRequestDto);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }


}
