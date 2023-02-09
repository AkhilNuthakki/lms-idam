package com.fse2.lms.service;

import com.fse2.lms.dto.request.LoginUserRequestDto;
import com.fse2.lms.dto.request.UserRequestDto;
import com.fse2.lms.exception.PasswordNotMatchedException;
import com.fse2.lms.exception.UserAlreadyExistsException;
import com.fse2.lms.exception.UserNotFoundException;
import com.fse2.lms.model.User;
import com.fse2.lms.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private static final UserRequestDto userRequestDto = UserRequestDto.buildUserRequestDtoWith()
            .userEmailId("akhil.nuthakki1@gmail.com")
            .userName("AkhilNuthakki")
            .password("Pass@2022").build();

    private static final LoginUserRequestDto loginUserRequestDto = LoginUserRequestDto.buildLoginUserRequestDtoWith()
            .userEmailId("akhil.nuthakki1@gmail.com")
            .password("Pass@2022").build();

    private static final User userWithWrongPassword = User.buildUserWith()
            .userEmailId("akhil.nuthakki1@gmail.com")
            .userName("AkhilNuthakki")
            .password("Pass@2021")
            .build();


    @Test
    void givenExistingUserEmailIdThenReturnUserAlreadyExistsException() {
        String anyString = anyString();
        when(userRepository.existsUserByUserEmailId(anyString)).thenReturn(true);
        Exception exception = assertThrows(UserAlreadyExistsException.class, () -> userService.addUser(userRequestDto));
        assertEquals("User already exists", exception.getMessage());
    }

    @Test
    void givenValidUserEmailIdThenAddUser() {
        when(userRepository.existsUserByUserEmailId(anyString())).thenReturn(false);
        when(userRepository.save(Mockito.any())).thenReturn(Mockito.any());
        userService.addUser(userRequestDto);
        verify(userRepository).save(Mockito.any());
    }

    @Test
    void giveUnavailableUserEmailIdThenReturnUserNotFoundException(){
        String anyString = anyString();
        when(userRepository.existsUserByUserEmailId(anyString)).thenReturn(false);
        Exception exception = assertThrows(UserNotFoundException.class, () -> userService.validateUser(loginUserRequestDto));
        assertEquals("User doesn't exists", exception.getMessage());
    }

    @Test
    void givenWrongPasswordThenReturnPasswordNotMatchedException(){
        String anyString = anyString();
        when(userRepository.existsUserByUserEmailId(anyString)).thenReturn(true);
        when(userRepository.findByUserEmailId(loginUserRequestDto.getUserEmailId())).thenReturn(userWithWrongPassword);
        Exception exception = assertThrows(PasswordNotMatchedException.class, () -> userService.validateUser(loginUserRequestDto));
        assertEquals("Password doesn't match", exception.getMessage());
    }


}
