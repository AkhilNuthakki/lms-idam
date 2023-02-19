package com.fse2.lms.service;

import com.fse2.lms.config.JWTAuthTokenConfiguration;
import com.fse2.lms.dto.request.LoginUserRequestDto;
import com.fse2.lms.dto.request.UserRequestDto;
import com.fse2.lms.dto.response.LoginUserResponseDto;
import com.fse2.lms.exception.PasswordNotMatchedException;
import com.fse2.lms.exception.UserAlreadyExistsException;
import com.fse2.lms.exception.UserNotFoundException;
import com.fse2.lms.model.User;
import com.fse2.lms.model.UserRole;
import com.fse2.lms.repository.UserRepository;
import com.nimbusds.jwt.SignedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTAuthTokenConfiguration jwtAuthTokenConfiguration;

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
                .userRole(String.valueOf(UserRole.USER))
                .build();
        LOG.info("User model built and inserting in database");
        userRepository.save(user);

    }

    public LoginUserResponseDto validateUser(LoginUserRequestDto userRequestDto) throws PasswordNotMatchedException, UserNotFoundException  {

        LOG.info("Checking if the user email Id exists in database");
        if (userRepository.existsUserByUserEmailId(userRequestDto.getUserEmailId())) {
            User user = userRepository.findByUserEmailId(userRequestDto.getUserEmailId());
            if (!user.getPassword().equals(userRequestDto.getPassword())) {
                LOG.info("Given password doesn't matched with the records");
                throw new PasswordNotMatchedException("Password doesn't match");
            } else {
                SignedJWT signedJWT = jwtAuthTokenConfiguration.generateToken(user.getUserRole());
                Date tokenExpirationDate = new Date();
                try {
                    tokenExpirationDate = signedJWT.getJWTClaimsSet().getExpirationTime();
                } catch (ParseException e) {
                    LOG.error("Parsing error");
                }

                return LoginUserResponseDto.buildLoginUserResponseDtoWith()
                        .userEmailId(user.getUserEmailId())
                        .userName(user.getUserName())
                        .userRole(user.getUserRole())
                        .token(signedJWT.serialize())
                        .tokenExpirationDate(tokenExpirationDate).build();
            }
        } else {
            LOG.info("User email Id not exists in database");
            throw new UserNotFoundException("User doesn't exists");
        }
    }
}
