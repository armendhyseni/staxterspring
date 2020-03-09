/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staxter.user.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Armend
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    protected ResponseEntity<Object> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        ExceptionReponse error = new ExceptionReponse();
        String[] values = ex.getMessage().split(";");

        error.setCode(values[0]);
        error.setDescription(values[1]);
        return buildResponseEntity(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LoginFailedException.class)
    protected ResponseEntity<Object> handleLoginFailed(LoginFailedException ex) {
        ExceptionReponse error = new ExceptionReponse();
        String[] values = ex.getMessage().split(";");

        error.setCode(values[0]);
        error.setDescription(values[1]);
        return buildResponseEntity(error, HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<Object> buildResponseEntity(ExceptionReponse apiError, HttpStatus status) {
        return new ResponseEntity<>(apiError, status);
    }
}
