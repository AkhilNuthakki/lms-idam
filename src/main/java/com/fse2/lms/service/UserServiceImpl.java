package com.fse2.lms.service;

import com.fse2.lms.dto.request.LoginUserRequestDto;
import com.fse2.lms.dto.request.UserRequestDto;
import com.fse2.lms.exception.PasswordNotMatchedException;
import com.fse2.lms.exception.UserAlreadyExistsException;
import com.fse2.lms.exception.UserNotFoundException;
import com.fse2.lms.model.User;
import com.fse2.lms.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    public void addUser(UserRequestDto userRequestDto) throws UserAlreadyExistsException {

        LOG.info("Checking if the user email Id exists in database");
        if (userRepository.existsUserByUserEmailId(userRequestDto.getUserEmailId())) {
            LOG.info("User email Id already exists");
            throw new UserAlreadyExistsException("User already exists");
        }
        LOG.info("User email Id not exists in database");

        User user = User.buildUserWith()
                .userEmailId(userRequestDto.getUserEmailId())
                .userName(userRequestDto.getUserName())
                .password(userRequestDto.getPassword())
                .build();
        LOG.info("User model built and inserting in database");
        userRepository.save(user);

    }

    public void validateUser(LoginUserRequestDto userRequestDto) throws PasswordNotMatchedException, UserNotFoundException  {

        LOG.info("Checking if the user email Id exists in database");
        if (userRepository.existsUserByUserEmailId(userRequestDto.getUserEmailId())) {
            User user = userRepository.findByUserEmailId(userRequestDto.getUserEmailId());
            if (!user.getPassword().equals(userRequestDto.getPassword())) {
                LOG.info("Given password doesn't matched with the records");
                throw new PasswordNotMatchedException("Password doesn't match");
            }
        } else {
            LOG.info("User email Id not exists in database");
            throw new UserNotFoundException("User doesn't exists");
        }
    }
}
