package com.fse2.lms.service;

import com.fse2.lms.dto.request.LoginUserRequestDto;
import com.fse2.lms.dto.request.UserRequestDto;
import com.fse2.lms.dto.response.LoginUserResponseDto;

public interface UserService {

    public void addUser(UserRequestDto userRequestDto);

    public LoginUserResponseDto validateUser(LoginUserRequestDto userRequestDto);


}
