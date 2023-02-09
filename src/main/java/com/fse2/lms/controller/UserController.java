package com.fse2.lms.controller;

import com.fse2.lms.dto.request.LoginUserRequestDto;
import com.fse2.lms.dto.request.UserRequestDto;
import com.fse2.lms.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1.0/lms/users")
@Tag(name = "Users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    @Operation(summary = "POST /register", description = "Register User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User added"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content()}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {@Content()}),
            @ApiResponse(responseCode = "409", description = "User already exists", content = {@Content()}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content()})
    })
    public ResponseEntity<String> addUser(@Valid @RequestBody UserRequestDto user) {
        userService.addUser(user);
        return new ResponseEntity<>("User added", HttpStatus.CREATED);

    }

    @PostMapping(value = "/login")
    @Operation(summary = "POST /login", description = "Authenticate User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authenticated"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content()}),
            @ApiResponse(responseCode = "403", description = "Password doesn't match", content = {@Content()}),
            @ApiResponse(responseCode = "404", description = "User doesn't exists", content = {@Content()}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content()})
    })
    public ResponseEntity<String> validateUser(@Valid @RequestBody LoginUserRequestDto user) {
        userService.validateUser(user);
        return new ResponseEntity<>("User authenticated", HttpStatus.OK);

    }


}
