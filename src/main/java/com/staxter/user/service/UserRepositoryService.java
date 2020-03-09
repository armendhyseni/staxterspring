/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.staxter.user.service;

import com.staxter.user.exception.LoginFailedException;
import com.staxter.user.exception.UserAlreadyExistsException;
import com.staxter.user.model.User;
import com.staxter.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Armend
 */
@Service
public class UserRepositoryService implements UserRepository {

    List<User> userList = new ArrayList<>();
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        Long existingUserName = this.userList.stream().filter(userNameCheck -> userNameCheck.getUserName().toLowerCase().equals(user.getUserName().toLowerCase())).count();
        if (existingUserName > 0L) {
            throw new UserAlreadyExistsException("USER_ALREADY_EXISTS", "A user with the given username already exists");
        } else {
            Integer biggestId = this.userList.stream().mapToInt(v -> Integer.parseInt(v.getId())).max().orElse(0);
            biggestId += 1;

            user.setId(biggestId.toString());
            user.setUserName(user.getUserName().toLowerCase());
            user.setHashedPassword(encoder.encode(user.getPlainTextPassword()));

            userList.add(user);

            return user;
        }
    }

    @Override
    public User loginUser(User user) throws LoginFailedException {
        User response = this.userList.stream().filter(userCheck
                -> userCheck.getUserName().equals(user.getUserName())
                && encoder.matches(user.getPlainTextPassword(), userCheck.getHashedPassword())).findFirst().orElse(null);

        if (response == null) {
            throw new LoginFailedException("USER_LOGIN_FAILED", "Incorrect username or password");
        } else {
            return response;
        }
    }

    @Override
    public List<User> getUsers() {
        return this.userList;
    }
}
