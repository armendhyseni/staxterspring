/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staxter.user.controller;

import com.staxter.user.exception.LoginFailedException;
import com.staxter.user.exception.UserAlreadyExistsException;
import com.staxter.user.model.User;
import com.staxter.user.model.UserUI;
import com.staxter.user.service.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Armend
 */
@RestController
@RequestMapping("/userservice")
public class UserController {

    @Autowired
    private UserRepositoryService userRepository;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody UserUI userUI) throws UserAlreadyExistsException {
        User user = new User();
        user.setPlainTextPassword(userUI.getPassword());
        user.setFirstName(userUI.getFirstName());
        user.setLastName(userUI.getLastName());
        user.setUserName(userUI.getUserName());
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.createUser(user));
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> loginUser(@RequestBody UserUI userUI) throws LoginFailedException {
        User user = new User();
        user.setPlainTextPassword(userUI.getPassword());
        user.setFirstName(userUI.getFirstName());
        user.setLastName(userUI.getLastName());
        user.setUserName(userUI.getUserName());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userRepository.loginUser(user));
    }
}
