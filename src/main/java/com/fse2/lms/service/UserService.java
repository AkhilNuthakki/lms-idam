package com.fse2.lms.service;

import com.fse2.lms.dto.request.LoginUserRequestDto;
import com.fse2.lms.dto.request.UserRequestDto;

public interface UserService {

    public void addUser(UserRequestDto userRequestDto);

    public void validateUser(LoginUserRequestDto userRequestDto);


}
