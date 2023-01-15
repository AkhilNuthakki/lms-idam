package com.fse2.lms.controller;

import com.fse2.lms.exception.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<Object> returnException(Exception ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }



}
