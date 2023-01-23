package com.fse2.lms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fse2.lms.controller.UserController;
import com.fse2.lms.dto.request.UserRequestDto;
import com.fse2.lms.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LmsSignUpApplicationTests {
    @Mock
    private UserController userController;

    @MockBean
    private UserServiceImpl userService;

    @Autowired
    private MockMvc mockMvc;

    private static final UserRequestDto userRequestDto = UserRequestDto.buildUserRequestDtoWith()
            .userEmailId("akhil.nuthakki1@gmail.com")
            .userName("Akhil Nuthakki")
            .password("Pass2022").build();

    private static final UserRequestDto invalidUserRequestDto = UserRequestDto.buildUserRequestDtoWith()
            .userEmailId("akhil.nuthakki1@gmail.com")
            .userName("Akhil Nuthakki")
            .password("Pass@2022").build();

    @Test
    void userAddedWhenProvidedValidUserDetails() throws Exception {
        Mockito.doNothing().when(userService).addUser(any(UserRequestDto.class));
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/lms/users/register")
                .content(asJsonString(userRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    void givenInValidUserDetailsThenReturnBadRequest() throws Exception {
        Mockito.doNothing().when(userService).addUser(any(UserRequestDto.class));
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/lms/users/register")
                .content(asJsonString(invalidUserRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    private static String asJsonString(final UserRequestDto obj) {
        try {
            return new ObjectMapper().writeValueAsString((obj));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
